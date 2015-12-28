package br.fa7.pod.ep2;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * BucketSort
 * Classe para ordenar uma lista de inteiros pelo método BucketSort
 * @author bruno, walter, washington
 * @since 12/10/2009
 */

public class BucketSort {

	/*
	 * Algoritmo
	 * 
	 * Entrada: um vetor L contendo n números no intervalo e de 0 até k
	 * Saída: o vetor L em ordem crescente
	 * 
	 * 	Para i=0 até n-1 // inicializando o bucket
	 * 		B[i]=nulo
	 * 	Para i=0 até n-1 // construindo listas ligadas
	 * 		Insira L[i] na lista ligada apontada por B[[L[i]*n/(k+1)]]
	 * 	J=0
	 * 	Para i=0 até n-1 // ordenação
	 * 		Coloque a lista ligada apontada por B[i] em ordem crescente
	 * 		p=B[i]
	 * 		enquanto p!=nulo
	 * 			L[j]=p.info
	 * 			J=j+1
	 * 			P=p.proximo
	 * 	Retorna a lista ligada apontada por B[i]
	 */
	
	/*
	 * Algoritmo da Wikipédia
	 * 
	 * 	function bucket-sort(array, n) is
	 * 		buckets ← new array of n empty lists
	 * 		for i = 0 to (length(array)-1) do
	 * 			insert array[i] into buckets[msbits(array[i], k)]
	 * 		for i = 0 to n - 1 do
	 * 			next-sort(buckets[i])
	 * 		return the concatenation of buckets[0], ..., buckets[n-1]
	 */
	
	public static int[] ordenar( int[] lista ){
		
		int n = lista.length;
		int k = n*10;
		int i;
		LinkedList<Integer>[] b = (LinkedList<Integer>[]) new LinkedList[n];
		
		for ( i = 0; i < b.length; i++) b[i] = new LinkedList();
		
		for ( i = 0; i < n; i++ )
			b[lista[i]*n/(k+1)].add(lista[i]);
		
		int j = 0;
		LinkedList<Integer> p;
		
		for ( i = 0; i < n; i++ ){
			p = ordenarListaEncadeada(b[i]);
			while ( !p.isEmpty() ){
				lista[j] = p.poll();
				j++;
			}
		}
		
		return lista;
	
	}
	
	//TODO: Verificar se melhor maneira de ordenar a lista enc. é pelo bolha com flag msm.
	public static LinkedList<Integer> ordenarListaEncadeada( LinkedList<Integer> p ){
		
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
				}
			}
			if ( flag == false )
				ordenado = true;
		}
		
		return p;
		
	}
	
}
