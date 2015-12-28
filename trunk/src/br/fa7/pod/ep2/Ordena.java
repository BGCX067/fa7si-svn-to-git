package br.fa7.pod.ep2; 
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;


public class Ordena {
	long tInicio, tFim,nMovimentos;
	int[]lista;

	
	public static void main(String[] args) {
		int tamanho = (args.length > 0) ? Integer.parseInt(args[0]) : 10;
		/*
		 * Tipo da lista: 1: Gerada automaticamente 2: Ordem decrescente 3:
		 * Ordem Crescente
		 */
		int tipo = (args.length > 1) ? Integer.parseInt(args[1]) : 1;

		Ordena listaOriginal = new Ordena(tamanho, tipo);
		
	
		System.out.print("CountingSort: ( " + listaOriginal.lista.length + " elementos  - "+getTipo(tipo) + " )" );
		Ordena listaCoutingSort = new Ordena(0,0);
		listaCoutingSort.lista  = listaOriginal.lista.clone();
		listaCoutingSort.escreveEmArquivo("CountingSort: ","", false);
		listaCoutingSort.imprimirLista("Antes: ", listaOriginal.lista);
		listaCoutingSort.coutingSort();
		
		System.out.print("BucketSort: ( " + listaOriginal.lista.length + " elementos  - "+getTipo(tipo) + " )" );
		Ordena listaBucketSort = new Ordena(0,0);
		listaBucketSort.lista  = listaOriginal.lista.clone();
		listaBucketSort.escreveEmArquivo("BucketSort: ","", false);
		listaBucketSort.imprimirLista("Antes: ", listaOriginal.lista);
		listaBucketSort.buckedSort();
		
		
		System.out.print("RadixSort: ( " + listaOriginal.lista.length + " elementos  - "+getTipo(tipo) + " )" );
		Ordena listaRadixSort = new Ordena(0,0);
		listaRadixSort.lista  = listaOriginal.lista.clone();
		listaRadixSort.escreveEmArquivo("BucketSort: ","", false);
		listaRadixSort.imprimirLista("Antes: ", listaOriginal.lista);
		listaRadixSort.buckedSort();
		
		
	
	}
	
/*
 * Construtor para a classe Ordena3
 */
	Ordena(int tamanho, int tipo){
		nMovimentos = 0;
		int i;
		this.lista = new int[tamanho];
		switch (tipo) {
		case 1:
			Random r = new Random();
			for (i = 0; i < tamanho; i++)
				this.lista[i] = r.nextInt(tamanho * 10);
			break;
		case 2:
			for (i = 0; i < tamanho; i++)
				this.lista[i] = tamanho - i;
			break;
		case 3:
			for (i = 0; i < tamanho; i++)
				this.lista[i] = i + 1;
			break;
		default:
			for (i = 0; i < tamanho; i++)
				this.lista[i] = (int) Math.random();
			break;
		}
	}	
/*
 * metodo para imprimir a lista na tela
 */
	public void imprimirLista(String msg2, int[] lista) {
		if (this.lista.length < 100)
			System.out.print("\n"+msg2);
			for (int i = 0; i < lista.length; i++)
				System.out.print(this.lista[i] + " ");

		escreveEmArquivo("",msg2,true);
	}

	/*
	 * msg = mensagem para escrever no arquivo
	 * lista =  a lista
	 * imprimeLista = se for true escreve a lista no arquivo senao escreve msg
	 */
	public void escreveEmArquivo(String msg1, String msg2,boolean imprimeLista) {
		try {
			// Gravando no arquivo
			FileWriter fw = new FileWriter(new File("Saida.TXT"), true);
			PrintWriter saida = new PrintWriter(fw);

			if (imprimeLista == true) {
				saida.print(msg2);
				for (int i = 0; i <= this.lista.length - 1; i++) {
					saida.print(" " + this.lista[i]);				
				}
			} else {
				saida.print(msg1);
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
	public void getDadosOrdenacao(){
		System.out.println("\nMovimentacoes: " + this.nMovimentos + " Tempo: " + (this.tFim - this.tInicio)/1000000 + " ms\n");
	}

	
	public int[] coutingSort( ){
		tInicio = System.nanoTime();
		
		int k = this.lista.length*10;
		int i;
		int[] c = new int[k];
		int[] aux = new int[k];
		
		for ( i = 0; i < k; i++ ) // Inicializa C
			c[i] = 0;
		for ( i = 0; i < this.lista.length; i++ ) // Conta
			c[this.lista[i]] = c[this.lista[i]] + 1;
		for ( i = 1; i < k; i++) // Soma Acumulada
			c[i] = c[i] + c[i-1];
		for ( i = this.lista.length-1; i >= 0; i-- ){ // Ordena
			aux[c[this.lista[i]]-1] = lista[i];
			c[this.lista[i]] = c[this.lista[i]]-1;
			nMovimentos = nMovimentos +2;
		}
		
		// Transfere
		for ( i = 0; i < lista.length; i++ ){
			lista[i] = aux[i];	
		}

		this.tFim = System.nanoTime();			
		imprimirLista( "Depois : ", lista);		
		getDadosOrdenacao();
		return lista;
	}
			
		
	/*
	 * 
	 * Metodo countingSort
	 * 
	 */
	
	public  int[] buckedSort(){
		this.tInicio = System.nanoTime();
		
		int n = lista.length;
		int k = n*10;
		int i;
		LinkedList<Integer>[] b = (LinkedList<Integer>[]) new LinkedList[n];
	
		for ( i = 0; i < b.length; i++) b[i] = new LinkedList<Integer>();
		
		for ( i = 0; i < n; i++ ){
			b[lista[i]*n/(k+1)].add(lista[i]);
			this.nMovimentos++;
		}
		int j = 0;
		LinkedList<Integer> p;
		
		for ( i = 0; i < n; i++ ){
			p = ordenarListaEncadeada(b[i]);
			while ( !p.isEmpty() ){
				lista[j] = p.poll();
				j++;
			}
		}
		this.tFim= System.nanoTime();
		imprimirLista("Depois : ", lista);		
		getDadosOrdenacao();
		return lista;	
	}
	/*
	 * 
	 * subrotina de ordenacao do countingSort
	 * 
	 */
	public LinkedList<Integer> ordenarListaEncadeada( LinkedList<Integer> p ){
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
					this.nMovimentos++;	
				}
			}
			if ( flag == false )
				ordenado = true;
		}
		return p;		
	}
	
	
	public void radixSort() {
		int[][] np = new int[this.lista.length][2];
		int[] q = new int[0x100];
		int i, j, k, l, f = 0;
		for (k = 0; k < 4; k++) {
			for (i = 0; i < (np.length - 1); i++)
				np[i][1] = i + 1;
			np[i][1] = -1;
			for (i = 0; i < q.length; i++)
				q[i] = -1;
			for (f = i = 0; i < this.lista.length; i++) {
				j = ((0xFF << (k << 3)) & this.lista[i]) >> (k << 3);
				if (q[j] == -1)
					l = q[j] = f;
				else {
					l = q[j];
					while (np[l][1] != -1)
						l = np[l][1];
					np[l][1] = f;
					l = np[l][1];
				}
				f = np[f][1];
				np[l][0] = this.lista[i];
				np[l][1] = -1;
				this.nMovimentos++;
			}
			for (l = q[i = j = 0]; i < 0x100; i++)
				for (l = q[i]; l != -1; l = np[l][1]){
					this.lista[j++] = np[l][0];
					nMovimentos++;
				}
		}
	}
	
	
	
	

}
