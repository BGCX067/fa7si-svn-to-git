package br.fa7.pod.ep2;

public class CountingSort {
	
	/*
	 * Algoritmo
	 * 
	 * Entrada: um vetor L com n números naturais no intervalo de 0 à k
	 * Saída: o vetor L em ordem crescente
	 * 
	 * 	Para i=0 até k //inicializando c
	 * 		C[i]=0
	 * 	Para i=0 até n-1 //contando
	 * 		C[L[i]]=c[L[i]] + 1
	 * 	Para i = 1 até k //soma acumulada
	 * 		C[i~] = c[i] + c[i-1]
	 * 	Para i=n-1 até 0 (passo-1) //ordenando
	 * 		Aux[c[L[i]]-1] = L[i]
	 * 		C[L[i]] = c[L[i]]-1
	 * 	Para i=0 até n-1
	 * 		L[i] = aux[i]
	 */
	
	public static int[] ordenar( int[] lista ){
		
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
		}
		for ( i = 0; i < lista.length; i++ ) // Transfere
			lista[i] = aux[i];
		
		return lista;
	}
	
}
