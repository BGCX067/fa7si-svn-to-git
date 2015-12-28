package br.fa7.pod.ep2;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * Ordena Segundo Trabalho de Pesquisa e Ordena√ß√£o de Dados
 * 
 * @author bruno; walter; washington;
 * @since 11/10/2009
 */
public class Ordena2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		deletaArquivo("Saida.txt");
		// Tamanho da lista
		int tamanho = (args.length > 0) ? Integer.parseInt(args[0]) : 10;

		/*
		 * Tipo da lista: 1: Gerada automaticamente 2: Ordem decrescente 3:
		 * Ordem Crescente
		 */
		int tipo = (args.length > 1) ? Integer.parseInt(args[1]) : 1;

		int[] lista = gerarLista(tamanho, tipo);

		System.out.println("CountingSort: ( " + lista.length + " elementos  - "+getTipo(tipo) + " )" );
		int[]listaCoutingSort = lista.clone();
		escreveEmArquivo("CountingSort: ","", listaCoutingSort,  false);
		imprimirLista("Antes: ", lista);
		coutingSort(listaCoutingSort);
		
		
		System.out.println("\nBuckedSort: ( " + lista.length + " elementos  - "+getTipo(tipo) + " )" );
		int[]listaBuckedSort = lista.clone();
		escreveEmArquivo("BuckedSort: ","", listaBuckedSort,  false);
		imprimirLista("Antes: ", lista);
		buckedSort(listaBuckedSort);
		

	}

	/**
	 * gerarLista gera uma lista de inteiros de acordo com os par√¢metros
	 * 
	 * @param tamanho
	 * @param tipo
	 * @return int[]
	 */
	public static int[] gerarLista(int tamanho, int tipo) {

		int i;
		int[] lista = new int[tamanho];

		switch (tipo) {
		case 1:
			Random r = new Random();
			for (i = 0; i < tamanho; i++)
				lista[i] = r.nextInt(tamanho * 10);
			break;
		case 2:
			for (i = 0; i < tamanho; i++)
				lista[i] = tamanho - i;
			break;
		case 3:
			for (i = 0; i < tamanho; i++)
				lista[i] = i + 1;
			break;
		default:
			for (i = 0; i < tamanho; i++)
				lista[i] = (int) Math.random();
			break;
		}
		return lista;
	}

	/**
	 * imprimirLista imprime a lista
	 * 
	 * @param lista
	 */
	public static void imprimirLista(String msg2, int[] lista) {
		for (int i = 0; i < lista.length; i++)
			System.out.print(lista[i] + " ");
		if (lista.length < 100)
			escreveEmArquivo("",msg2, lista, true);
	}

	/**
	 * msg = mensagem para escrever no arquivo
	 * lista =  a lista
	 * imprimeLista = se for true escreve a lista no arquivo senao escreve msg
	 */
	public static void escreveEmArquivo(String msg1, String msg2, int[] lista,
			boolean imprimeLista) {
		try {
			// Gravando no arquivo
			FileWriter fw = new FileWriter(new File("Saida.TXT"), true);
			PrintWriter saida = new PrintWriter(fw);

			if (imprimeLista == true) {
				saida.print(msg2);
				for (int i = 0; i <= lista.length - 1; i++) {
					saida.print(" " + lista[i]);				
				}
				saida.println("");
			} else {
				saida.println(msg1);
			}

			saida.close();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
	/* 
	 * Metodo para remover o arquivo saida.txt se ele ja 
	 * existir
	 */
	public static void deletaArquivo(String nmArq){
		File arq = new File(nmArq);
		arq.delete();
	}
	
	public static String getTipo(int tipo){
		if (tipo == 1) return  "aleatoria";
		if (tipo == 1) return  "Crescente";
		return "Decrescente";
	}
	public static void getDadosOrdenacao(long t1, long t2, long movimentacoes){
		System.out.println("\nMovimentacoes: " + movimentacoes + " Tempo: " + (t2-t1)/1000000 + " ms");
	}
	

	
	
	/*
	 * 
	 *  os  metodos de ordenacao 
	 * 
	 * 
	 */
	
	
	
	/**
	 * Metodo de ordenacao CountingSort
	 */
	public static int[] coutingSort( int[] lista ){
		long t1 = System.nanoTime();
		long movimentacoes = 0;
		int k = lista.length*10;
		int i;
		int[] c = new int[k];
		int[] aux = new int[k];
		
		for ( i = 0; i < k; i++ ) // Inicializa C
			c[i] = 0;
		for ( i = 0; i < lista.length; i++ ) // Conta
			c[lista[i]] = c[lista[i]] + 1;
		for ( i = 1; i < k; i++) // Soma Acumulada
			c[i] = c[i] + c[i-1];
		for ( i = lista.length-1; i >= 0; i-- ){ // Ordena
			aux[c[lista[i]]-1] = lista[i];
			c[lista[i]] = c[lista[i]]-1;
			
			movimentacoes = movimentacoes +2;
		}
		
		// Transfere
		for ( i = 0; i < lista.length; i++ ){
			lista[i] = aux[i];	
		}

		long t2 = System.nanoTime();			
		imprimirLista( "Depois : ", lista);		
		getDadosOrdenacao(t1,t2,movimentacoes);
		return lista;
	}
	
	
	
	
	
	public static int[] buckedSort( int[] lista ){
		long movimentacoes = 0;
		long t1 = System.nanoTime();
		
		int n = lista.length;
		int k = n*10;
		int i;
		LinkedList<Integer>[] b = (LinkedList<Integer>[]) new LinkedList[n];
		
		for ( i = 0; i < b.length; i++) b[i] = new LinkedList<Integer>();
		
		for ( i = 0; i < n; i++ ){
			b[lista[i]*n/(k+1)].add(lista[i]);
			movimentacoes++;
		}
		int j = 0;
		LinkedList<Integer> p;
		
		for ( i = 0; i < n; i++ ){
			p = ordenarListaEncadeada(b[i], movimentacoes);
			while ( !p.isEmpty() ){
				lista[j] = p.poll();
				j++;
			}
		}
		long t2 = System.nanoTime();
		imprimirLista( "Depois : ", lista);		
		getDadosOrdenacao(t1,t2,movimentacoes);
		return lista;	
	}
	
	// Subrotina de ordenaÁ„o do BucketSort usando bolha com Flag.
	public static LinkedList<Integer> ordenarListaEncadeada( LinkedList<Integer> p, long movimentacoes ){
		boolean ordenado = false;
		boolean flag;
		int i;
		int aux;
		while( ordenado == false ){
			flag = false;
			for ( i = 1; i < p.size(); i++ ){
				if( p.get(i) < p.get(i-1) ){
					aux = p.get(i);
					p.set(i, p.get(i-1));
					p.set(i-1, aux);
					flag = true;
					movimentacoes++;	
				}
			}
			if ( flag == false )
				ordenado = true;
		}
		return p;		
	}

	
	
	
}
