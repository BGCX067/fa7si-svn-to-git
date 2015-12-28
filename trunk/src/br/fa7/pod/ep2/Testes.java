package br.fa7.pod.ep2;

public class Testes {
	
	public static void main(String[] args) {
		
		int[] lista = new int[30];
		int j;		
		for( int i = 0;i<lista.length;i++){
			j = (int) (Math.random() * lista.length * 10) ;
			lista[i] =    j;
		}
		for( int i = 0 ;i<lista.length;i++)
			System.out.print(" " + lista[i]);
	
		RadixSort l = new RadixSort();
		l.ordenar(lista);
		
		System.out.print("\n ");		
		for( int i = 0 ;i<lista.length;i++)
			System.out.print(" " + lista[i]);

		
		
	} 

}
