# Trabalho 1 - Componentes Conexas

Escreva um programa que receba um grafo não-direcionado e que escreva as suas componentes conexas. Uma componente conexa é um conjunto maximal de vértices ligados por caminhos no grafo. Assim, por exemplo, se a entrada for (no formato explicado adiante) o grafo

```
2--9--6

3--8--10

5--7

1     4 
```

então a saída, conforme o formato explicado adiante, deve ser

```
1
2 6 9
3 8 10
4
5 7
```

## Formato da Entrada

A entrada, recebida através da entrada padrão, estará no formato UCINET DL, lista de arestas ("edgelist1"), sem rótulos para os vértices e sem pesos para as arestas, conforme o seguinte exemplo, que é uma representação para o grafo acima:

```
dl
format=edgelist1
n=10
data:
2 9
3 8
5 7
6 9
8 10
```

Observe que, no formato acima, os vértices são numerados a partir de 1.
##Formato da Saída

A saída, fornecida através da saída padrão, tem que estar no formato ilustrado pelo seguinte exemplo, que é a saída esperada para a entrada acima:

```
1
2 6 9
3 8 10
4
5 7
```