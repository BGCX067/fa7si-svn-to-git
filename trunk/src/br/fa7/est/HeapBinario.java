package br.fa7.est;

/**
 * Heap Binário
 * @author bruno, walter, washington
 * @since 16/10/2009
 */
public class HeapBinario {

	public int[] vetor;
	public int ultima;
	private int tamanho;
	
	/**
	 * Construtor
	 * @param n Tamanho
	 */
	public HeapBinario( int n ){
		
		vetor = new int[n+1];
		tamanho = n;
		ultima = 0;
		
	}
	
	/**
	 * Insere um número no heap
	 * @param x
	 * @throws Exception
	 */
	public void insere_heap( int x ) throws Exception{
		
		if ( ultima == tamanho )
			throw new Exception("Heap Overflow");
		
		ultima++;
		int i = ultima;
		while ( i > 1 && x < vetor[i/2] ){
			vetor[i] = vetor[i/2];
			i = i/2;
		}
		vetor[i] = x;
		
	}
	
	/**
	 * Remove e retorna o menor valor do heap
	 * @return menor
	 * @throws Exception
	 */
	public int remove_menor() throws Exception{
		
		if ( ultima == 0 ) // heap overflow
			throw new Exception("Heap overflow");
		
		int menor = vetor[1];
		vetor[1] = vetor[ultima];
		ultima = ultima - 1;
		int i = 1;
		int min;
		while ( ( i <= ultima/2 && vetor[i] > vetor[2*i] ) || ( i <= ultima/2 && vetor[i] > vetor[2*i+1] ) ){
			min = 2*i;
			if ( i < ultima/2 && vetor[2*i+1] <= vetor[2*i] )
				min++;
			troca( i, min );
			i = min;
		}
		return menor;
		
	}
	
	/**
	 * Troca o valor de duas posicoes do vetor
	 * @param pos1
	 * @param pos2
	 */
	private void troca( int pos1, int pos2 ){
		
		int aux = vetor[pos1];
		vetor[pos1] = vetor[pos2];
		vetor[pos2] = aux;
		
	}
	
}
