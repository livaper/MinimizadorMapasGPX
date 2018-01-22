# MinimizadorMapasGPX
Aplicação de minimização de pontos marcados no mapa no formato GPX, como pré-requisito da disciplina Programação Modular, 2015.2, UNIRIO, Prof. Márcio Barros

 A minimização consiste em reduzir a quantidade de pontos presentes em uma trajetória realizada em um mapa.
 
 Inicialmente o usuário entrará via teclado a opção que deseja utilizar na minimização.
 A redução poderá ser feita de duas formas. 
 
 A primeira forma consistirá no recebimento de um valor em metros via teclado.
 Desta forma a minimização será feita descartando os pontos que estiverem em uma distância do arco formado pelos pontos adjacentes menor ou igual a uma distância limite 
 espeficicada pelo usuário.
 
 A segunda forma consistirá no recebimento de um valor em porcentagem via teclado (valor entre 0 e 100).
 Desta forma aminimização será feita mantendo na trajetória apenas o percentual de pontos informado, ou seja, descartando "100 menos o valor entrado no teclado %" de pontos que estiverem mais próximos do arco formada pelos pontos adjacentes.
 
 A trajetória será lida a partir de um arquivo de formato GPX e a trajetória reduzida será escrita em um novo arquivo de mesmo formato.
 Tanto o arquivo de entrada quanto o de saída podem ser abertos no aplicativo Google Earth, para a visualização das respectivas trajetórias no mapa Mundi.
