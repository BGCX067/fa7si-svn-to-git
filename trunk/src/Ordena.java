// Autores : Walter , Bruno, Washington 

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;



class Ordena {
	double tInicio, tFim, nMovimentos;
	int[] lista;
	/* atributos do heap */
	int[] heap;
	int tamHeap;
	int ultPosHeap;

	public static void main(String[] args) throws Exception {
		int tamanho = (args.length > 0) ? Integer.parseInt(args[0]) : 10;
		/*
		 * Tipo da lista: 1: Gerada automaticamente 2: Ordem decrescente 3:
		 * Ordem Crescente
		 */
		int tipo = (args.length > 1) ? Integer.parseInt(args[1]) : 1;

		tamanho = 2000;
		tipo = 3;

		Ordena listaOriginal = new Ordena(tamanho, tipo);

		System.out.print("CountingSort: ( " + listaOriginal.lista.length
				+ " elementos  - " + getTipo(tipo) + " )");
		Ordena listaCoutingSort = new Ordena(0, 0);
		listaCoutingSort.lista = (int[]) listaOriginal.lista.clone();
		listaCoutingSort.escreveEmArquivo("CountingSort: ", "", false);
		listaCoutingSort.imprimirLista("Antes: ", listaOriginal.lista);
		listaCoutingSort.coutingSort();

		System.out.print("BucketSort: ( " + listaOriginal.lista.length
				+ " elementos  - " + getTipo(tipo) + " )");
		Ordena listaBucketSort = new Ordena(0, 0);
		listaBucketSort.lista = (int[]) listaOriginal.lista.clone();
		listaBucketSort.escreveEmArquivo("BucketSort: ", "", false);
		listaBucketSort.imprimirLista("Antes: ", listaOriginal.lista);
		listaBucketSort.bucketSort();

		System.out.print("RadixSort: ( " + listaOriginal.lista.length
				+ " elementos  - " + getTipo(tipo) + " )");
		Ordena listaRadixSort = new Ordena(0, 0);
		listaRadixSort.lista = (int[]) listaOriginal.lista.clone();
		listaRadixSort.escreveEmArquivo("RadixSort: ", "", false);
		listaRadixSort.imprimirLista("Antes: ", listaOriginal.lista);
		listaRadixSort.radixSort();

		System.out.print("HeapSort: ( " + listaOriginal.lista.length
				+ " elementos  - " + getTipo(tipo) + " )");
		Ordena listaHeapSort = new Ordena(0, 0);
		listaHeapSort.lista = (int[]) listaOriginal.lista.clone();
		listaHeapSort.escreveEmArquivo("HeapSort: ", "", false);
		listaHeapSort.imprimirLista("Antes: ", listaOriginal.lista);
		listaHeapSort.heapSort();

	}

	/*
	 * Construtor para a classe Ordena3
	 */
	Ordena(int tamanho, int tipo) {
		nMovimentos = 0;
		int i;
		this.lista = new int[tamanho];
		switch (tipo) {
		case 1: // popular as listas aleatorias
			Random r = new Random();
			for (i = 0; i < tamanho; i++)
				this.lista[i] = r.nextInt(tamanho * 10);
			break;
		case 2: // popular a lista decrescente
			for (i = 0; i < tamanho; i++)
				this.lista[i] = tamanho - i;
			break;
		case 3:// popular a list crescente
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
			System.out.print("\n" + msg2);
		for (int i = 0; i < lista.length; i++)
			System.out.print(this.lista[i] + " ");

		escreveEmArquivo("", msg2, true);
	}

	/*
	 * metodo para escrever as informaces no arquivo saida.txt msg = mensagem
	 * para escrever no arquivo lista = a lista imprimeLista = se for true
	 * escreve a lista no arquivo senao escreve msg
	 */
	public void escreveEmArquivo(String msg1, String msg2, boolean imprimeLista) {
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
	 * Metodo para remover o arquivo saida.txt no inicio da execucao, se ele ja
	 * existir
	 */
	public static void deletaArquivo(String nmArq) {
		File arq = new File(nmArq);
		arq.delete();
	}

	public static String getTipo(int tipo) {
		if (tipo == 1)
			return "aleatoria";
		if (tipo == 1)
			return "Crescente";
		return "Decrescente";
	}

	/*
	 * metodo paa capturar os dados da ordenacao
	 */

	public void getDadosOrdenacao() {
		System.out.println("\nMovimentacoes: " + this.nMovimentos + " Tempo: "
				+ (this.tFim - this.tInicio) / 1000000 + " ms \n");
	} 

	
	public int[] coutingSort() {
		this.tInicio = System.nanoTime();

		int k = this.lista.length * 10;
		int i;
		int[] c = new int[k];
		int[] aux = new int[k];

		for (i = 0; i < k; i++)
			// Inicializa C
			c[i] = 0;
		for (i = 0; i < this.lista.length; i++)
			// Conta
			c[this.lista[i]] = c[this.lista[i]] + 1;
		for (i = 1; i < k; i++)
			// Soma Acumulada
			c[i] = c[i] + c[i - 1];
		for (i = this.lista.length - 1; i >= 0; i--) { // Ordena
			aux[c[this.lista[i]] - 1] = lista[i];
			c[this.lista[i]] = c[this.lista[i]] - 1;
			this.nMovimentos = this.nMovimentos + 2;
		}

		// Transfere para a lista
		for (i = 0; i < lista.length; i++) {
			lista[i] = aux[i];
		}

		this.tFim = System.nanoTime();
		imprimirLista("Depois : ", lista);
		getDadosOrdenacao();
		return lista;
	}

	/*
	 * 
	 * Metodo de ordenacao bucketSort
	 */
	public void bucketSort() {
		this.nMovimentos = 0;
		int n = this.lista.length;
		int k = n * 10;
		int i;
		// Cria um array de listas ligadas
		LinkedList[] b = (LinkedList[]) new LinkedList[n];
		// para cada item de b, cria outra lista ligada
		for (i = 0; i < b.length; i++)
			b[i] = new LinkedList();

		// Adiciona os valores na lista ligada
		for (i = 0; i < n; i++) {
			b[this.lista[i] * n / (k + 1)].add(new Integer(this.lista[i]));
			this.nMovimentos++;
		}

		int j = 0;
		LinkedList p;

		// Ordena as listas ligadas
		for (i = 0; i < n; i++) {
			p = ordenarListaEncadeada(b[i]);
			while (!p.isEmpty()) {
				this.nMovimentos++;
				this.lista[j] = ((Integer) p.removeFirst()).intValue();
				j++;
			}
		}
		this.tFim = System.nanoTime();
		imprimirLista("Depois : ", lista);
		getDadosOrdenacao();
	}

	/*
	 * 
	 * subrotina de ordenacao do bucketSort
	 */
	public LinkedList ordenarListaEncadeada(LinkedList p) {

		boolean ordenado = false;
		boolean flag;
		int i;
		int aux;
		while (ordenado == false) {
			flag = false;
			for (i = 1; i < p.size(); i++) {
				if (((Integer) p.get(i)).intValue() < ((Integer) p.get(i - 1))
						.intValue()) {
					this.nMovimentos = this.nMovimentos + 3;
					aux = ((Integer) p.get(i)).intValue();
					p.set(i, p.get(i - 1));
					p.set(i - 1, new Integer(aux));
					flag = true;
				}
			}
			if (flag == false)
				ordenado = true;
		}

		return p;

	}

	public void radixSort() {
		tInicio = System.nanoTime();

		int[][] np = new int[this.lista.length][2];
		int[] q = new int[0x100]; // criar o vetor binario
		int i, j, k, l, f = 0;
		for (k = 0; k < 4; k++) {
			for (i = 0; i < (np.length - 1); i++)
				np[i][1] = i + 1;
			np[i][1] = -1;
			for (i = 0; i < q.length; i++)
				q[i] = -1;
			for (f = i = 0; i < this.lista.length; i++) {
				j = ((0xFF << (k << 3)) & this.lista[i]) >> (k << 3);
				if (q[j] == -1) {
					this.nMovimentos++;
					l = q[j] = f;
				} else {
					l = q[j];
					while (np[l][1] != -1)
						l = np[l][1];
					np[l][1] = f;
					l = np[l][1];
					this.nMovimentos++;
				}
				f = np[f][1];
				np[l][0] = this.lista[i];
				np[l][1] = -1;
				this.nMovimentos++;
			}
			for (l = q[i = j = 0]; i < 0x100; i++)
				for (l = q[i]; l != -1; l = np[l][1]) {
					this.lista[j++] = np[l][0];
					this.nMovimentos++;
				}
		}
		this.tFim = System.nanoTime();
		imprimirLista("Depois : ", lista);
		getDadosOrdenacao();

	}

	void trocaPosHeap(int pos1, int pos2) {
		int aux = this.heap[pos1];
		this.heap[pos1] = this.heap[pos2];
		this.heap[pos2] = aux;
		this.nMovimentos = this.nMovimentos + 3;
	}

	public void insere_heap(int x) throws Exception {

		if (this.ultPosHeap == this.tamHeap)
			throw new Exception("Heap Overflow");

		this.ultPosHeap++;
		int i = this.ultPosHeap;
		while (i > 1 && x < this.heap[i / 2]) {
			this.heap[i] = this.heap[i / 2];
			i = i / 2;
			this.nMovimentos++;
		}
		this.heap[i] = x;
	}

	public int remove_menor() throws Exception {
		if (this.ultPosHeap == 0) // heap overflow
			throw new Exception("Heap overflow");

		int menor = this.heap[1];
		this.heap[1] = this.heap[this.ultPosHeap];
		this.ultPosHeap--;
		int i = 1;
		int min;
		while ((i <= this.ultPosHeap / 2 && this.heap[i] > this.heap[2 * i])
				|| (i <= this.ultPosHeap / 2 && this.heap[i] > this.heap[2 * i + 1])) {
			min = 2 * i;
			if (i < this.ultPosHeap / 2
					&& this.heap[2 * i + 1] <= this.heap[2 * i])
				min++;
			trocaPosHeap(i, min);
			i = min;
		}
		return menor;
	}

	public void heapSort() throws Exception {
		int n = this.lista.length;
		int i;

		this.heap = new int[this.lista.length + 1];
		this.tamHeap = this.lista.length;
		this.ultPosHeap = 0;

		for (i = 0; i < n; i++)
			this.insere_heap(lista[i]);
		for (i = 0; i < n; i++)
			this.lista[i] = this.remove_menor();

		this.tFim = System.nanoTime();
		imprimirLista("Depois : ", lista);
		getDadosOrdenacao();
	}
}
