package br.fa7.pod.ep2;

import br.fa7.est.HeapBinario;

public class HeapSort {

	/*
	 * Algoritmo
	 * 	heapsort
	 * 		Entrada: uma lista L com n posicoes
	 * 		Saída: o vetor L em ordem crescente
	 * 		inicializa_heap(h,n)
	 * 		para i = 0 até n-1
	 * 			insere_heap(h,L[i])
	 * 		para i = 0 até n-1
	 * 			L[i] = remove_menor(h)
	 * 		devolva L
	 */	
	
	public static int[] ordenar( int[] lista ) throws Exception{
		
		int n = lista.length;
		int i;
		
		HeapBinario h = new HeapBinario( n );
		
		for ( i = 0; i < n; i++ )
			h.insere_heap( lista[i] );
		for ( i = 0; i < n; i++ )
			lista [i] = h.remove_menor();
		
		return lista;
	
	}
	
}
