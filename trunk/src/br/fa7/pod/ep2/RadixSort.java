package br.fa7.pod.ep2;

public class RadixSort {

	/**
	 * radixSort
	 * Classe para ordenar uma lista de inteiros pelo radixSort
	 * @author bruno, walter, washington
	 * @since 15/10/2009
	 */

	public int[] ordenar(int[] lista) {
		int[][] np = new int[lista.length][2];
		int[] q = new int[0x100];
		int i, j, k, l, f = 0;
		for (k = 0; k < 4; k++) {
			for (i = 0; i < (np.length - 1); i++)
				np[i][1] = i + 1;
			np[i][1] = -1;
			for (i = 0; i < q.length; i++)
				q[i] = -1;
			for (f = i = 0; i < lista.length; i++) {
				j = ((0xFF << (k << 3)) & lista[i]) >> (k << 3);
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
				np[l][0] = lista[i];
				np[l][1] = -1;
			}
			for (l = q[i = j = 0]; i < 0x100; i++)
				for (l = q[i]; l != -1; l = np[l][1])
					lista[j++] = np[l][0];
		}
		return lista;
	}
}
