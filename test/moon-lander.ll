; ModuleID = 'programa.bc'

@COR_PRETO = global i32 0
@TECLA_W = global i32 0
@TECLA_SETA_ACIMA = global i32 0
@TECLA_ESC = global i32 0
@TECLA_A = global i32 0
@TECLA_SETA_ESQUERDA = global i32 0
@TECLA_ENTER = global i32 0
@TECLA_D = global i32 0
@TECLA_SETA_DIREITA = global i32 0
@LARGURA_TELA = global i32 800
@ALTURA_TELA = global i32 600
@ACELERACAO_GRAVIDADE = global double 1.800000e-01
@VELOCIDADE_MAXIMA_GRAVIDADE = global double 4.500000e+00
@ACELERACAO_FOGUETE = global double 2.000000e-01
@VELOCIDADE_MAXIMA_FOGUETE = global double 2.000000e+01
@PERCENTUAL_VELOCIDADE_HORIZONTAL = global double 1.000000e+00
@VELOCIDADE_MAXIMA_POUSO = global double 2.500000e+00
@ALTURA_FOGUETE = global i32 76
@LARGURA_FOGUETE = global i32 59
@LARGURA_PLATAFORMA = global i32 135
@TAXA_ATUALIZACAO = global i32 85
@x_foguete = global i32 0
@y_foguete = global i32 0
@x_plataforma = global i32 350
@y_plataforma = global i32 532
@velocidade_vertical = global double 0.000000e+00
@velocidade_horizontal = global double 0.000000e+00
@acelerando = global i1 false
@quebrou = global i1 false
@pousou = global i1 false
@foi_para_o_espaco = global i1 false
@tempo_inicio_jogo = global i32 0
@tempo_total_jogo = global i32 0
@tempo_inicio = global i32 0
@tempo_decorrido = global i32 0
@tempo_restante = global i32 0
@tempo_quadro = global i32 11
@imagem_fundo = global i32 0
@imagem_menu = global i32 0
@imagem_foguete = global i32 0
@imagem_lua = global i32 0
@imagem_planetas = global i32 0
@imagem_jato2 = global i32 0
@imagem_foguete_quebrado = global i32 0
@imagem_jato = global i32 0
@imagem_plataforma = global i32 0
@imagem_fogo = global i32 0
@indice_fogo = global i32 0
@atualizou = global i1 false
@indice_fundo = global i32 0
@ultimo_giro_fundo = global i32 0
@indice_planetas = global i32 0
@ultimo_giro_planetas = global i32 0
@0 = private unnamed_addr constant [9 x i8] c"moon.png\00"
@1 = private unnamed_addr constant [10 x i8] c"fundo.jpg\00"
@2 = private unnamed_addr constant [13 x i8] c"planetas.png\00"
@3 = private unnamed_addr constant [9 x i8] c"menu.jpg\00"
@4 = private unnamed_addr constant [12 x i8] c"foguete.png\00"
@5 = private unnamed_addr constant [18 x i8] c"jato_foguete1.png\00"
@6 = private unnamed_addr constant [21 x i8] c"plataforma_pouso.png\00"
@7 = private unnamed_addr constant [18 x i8] c"jato_foguete2.png\00"
@8 = private unnamed_addr constant [21 x i8] c"foguete_quebrado.png\00"
@9 = private unnamed_addr constant [9 x i8] c"fogo.png\00"

declare i32 @escreva(i8*, ...)

declare void @leia(i32, ...)

declare void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.limpar()

declare i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.carregar_imagem(i8*)

declare i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.criar_cor(i32, i32, i32)

declare void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.definir_cor(i32)

declare void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_ponto(i32, i32)

declare void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_imagem(i32, i32, i32)

declare void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.renderizar_imagem(i32, i32)

declare i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.renderizar()

declare i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.liberar_imagem(i32)

declare void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.iniciar_modo_grafico(i1)

declare void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.encerrar_modo_grafico()

declare void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_elipse(i32, i32, i32, i32, i1)

declare void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_porcao_imagem(i32, i32, i32, i32, i32, i32, i32)

declare i1 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTeclado.tecla_pressionada(i32)

declare void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaUtil.aguarde(i32)

declare i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaUtil.sorteia(i32, i32)

declare i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaUtil.tempo_decorrido()

declare i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaUtil.arredondar(double, i32)

declare i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTipos.real_para_inteiro(double)

declare double @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaMatematica.arredondar(double, i32)

declare i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaMatematica.valor_absoluto(double)

define void @jogo() {
incio_funcao:
  call void @reiniciar()
  br label %enquanto.condicao

enquanto.condicao:                                ; preds = %senao, %incio_funcao
  %TECLA_ESC = load i32* @TECLA_ESC
  %0 = call i1 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTeclado.tecla_pressionada(i32 %TECLA_ESC)
  %1 = sub i1 false, %0
  br i1 %1, label %enquanto.entrada, label %enquanto.saida

enquanto.entrada:                                 ; preds = %enquanto.condicao
  %2 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaUtil.tempo_decorrido()
  store i32 %2, i32* @tempo_inicio
  call void @atualizar()
  call void @desenhar()
  %3 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaUtil.tempo_decorrido()
  %tempo_inicio = load i32* @tempo_inicio
  %4 = sub i32 %3, %tempo_inicio
  store i32 %4, i32* @tempo_decorrido
  %tempo_quadro = load i32* @tempo_quadro
  %tempo_decorrido = load i32* @tempo_decorrido
  %5 = sub i32 %tempo_quadro, %tempo_decorrido
  store i32 %5, i32* @tempo_restante
  %TAXA_ATUALIZACAO = load i32* @TAXA_ATUALIZACAO
  %6 = icmp sgt i32 %TAXA_ATUALIZACAO, 0
  %tempo_restante = load i32* @tempo_restante
  %7 = icmp sgt i32 %tempo_restante, 0
  %8 = and i1 %6, %7
  br i1 %8, label %se, label %senao

enquanto.saida:                                   ; preds = %enquanto.condicao
  br label %saida

pulo:                                             ; No predecessors!
  %tempo_restante1 = load i32* @tempo_restante
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaUtil.aguarde(i32 %tempo_restante1)
  br label %saida

se:                                               ; preds = %enquanto.entrada
  br label %saida

senao:                                            ; preds = %enquanto.entrada
  br label %enquanto.condicao

saida:                                            ; preds = %se, %pulo, %enquanto.saida
}

define void @atualizar() {
incio_funcao:
  %pousou = load i1* @pousou
  %0 = sub i1 false, %pousou
  %quebrou = load i1* @quebrou
  %1 = sub i1 false, %quebrou
  %2 = and i1 %0, %1
  %foi_para_o_espaco = load i1* @foi_para_o_espaco
  %3 = sub i1 false, %foi_para_o_espaco
  %4 = and i1 %2, %3
  br i1 %4, label %se, label %senao

pulo:                                             ; No predecessors!
  br label %saida

se:                                               ; preds = %incio_funcao
  call void @atualizar_velocidade_vertical()
  call void @atualizar_velocidade_horizontal()
  call void @atualizar_posicao_foguete()
  call void @atualizar_estado_foguete()
  br label %saida

senao:                                            ; preds = %incio_funcao
  %TECLA_ENTER = load i32* @TECLA_ENTER
  %5 = call i1 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTeclado.tecla_pressionada(i32 %TECLA_ENTER)
  br i1 %5, label %se2, label %senao3

saida:                                            ; preds = %senao3, %se, %pulo
  br label %saida4

pulo1:                                            ; No predecessors!
  call void @reiniciar()
  br label %saida4

se2:                                              ; preds = %senao
  br label %saida4

senao3:                                           ; preds = %senao
  br label %saida

saida4:                                           ; preds = %se2, %pulo1, %saida
}

define void @atualizar_velocidade_vertical() {
incio_funcao:
  %TECLA_W = load i32* @TECLA_W
  %0 = call i1 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTeclado.tecla_pressionada(i32 %TECLA_W)
  %TECLA_SETA_ACIMA = load i32* @TECLA_SETA_ACIMA
  %1 = call i1 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTeclado.tecla_pressionada(i32 %TECLA_SETA_ACIMA)
  %2 = or i1 %0, %1
  br i1 %2, label %se, label %senao

pulo:                                             ; No predecessors!
  br label %saida

se:                                               ; preds = %incio_funcao
  %velocidade_vertical = load double* @velocidade_vertical
  %ACELERACAO_FOGUETE = load double* @ACELERACAO_FOGUETE
  %3 = fsub double %velocidade_vertical, %ACELERACAO_FOGUETE
  store double %3, double* @velocidade_vertical
  %velocidade_vertical5 = load double* @velocidade_vertical
  %VELOCIDADE_MAXIMA_FOGUETE = load double* @VELOCIDADE_MAXIMA_FOGUETE
  %4 = fsub double -0.000000e+00, %VELOCIDADE_MAXIMA_FOGUETE
  %5 = fcmp ult double %velocidade_vertical5, %4
  br i1 %5, label %se2, label %senao3

senao:                                            ; preds = %incio_funcao
  br label %saida4

saida:                                            ; preds = %senao10, %saida4, %pulo
  %velocidade_vertical7 = load double* @velocidade_vertical
  %ACELERACAO_GRAVIDADE = load double* @ACELERACAO_GRAVIDADE
  %6 = fadd double %velocidade_vertical7, %ACELERACAO_GRAVIDADE
  store double %6, double* @velocidade_vertical
  %velocidade_vertical12 = load double* @velocidade_vertical
  %VELOCIDADE_MAXIMA_GRAVIDADE = load double* @VELOCIDADE_MAXIMA_GRAVIDADE
  %7 = fcmp ogt double %velocidade_vertical12, %VELOCIDADE_MAXIMA_GRAVIDADE
  br i1 %7, label %se9, label %senao10

pulo1:                                            ; No predecessors!
  br label %saida11

se2:                                              ; preds = %se
  %VELOCIDADE_MAXIMA_FOGUETE6 = load double* @VELOCIDADE_MAXIMA_FOGUETE
  %8 = fsub double -0.000000e+00, %VELOCIDADE_MAXIMA_FOGUETE6
  store double %8, double* @velocidade_vertical
  br label %saida4

senao3:                                           ; preds = %se
  br label %saida4

saida4:                                           ; preds = %senao3, %se2, %senao
  store i1 true, i1* @acelerando
  br label %saida

pulo8:                                            ; No predecessors!
  %VELOCIDADE_MAXIMA_GRAVIDADE13 = load double* @VELOCIDADE_MAXIMA_GRAVIDADE
  store double %VELOCIDADE_MAXIMA_GRAVIDADE13, double* @velocidade_vertical
  br label %saida11

se9:                                              ; preds = %saida
  br label %saida11

senao10:                                          ; preds = %saida
  store i1 false, i1* @acelerando
  br label %saida

saida11:                                          ; preds = %se9, %pulo8, %pulo1
}

define void @atualizar_velocidade_horizontal() {
incio_funcao:
  %TECLA_A = load i32* @TECLA_A
  %0 = call i1 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTeclado.tecla_pressionada(i32 %TECLA_A)
  %TECLA_SETA_ESQUERDA = load i32* @TECLA_SETA_ESQUERDA
  %1 = call i1 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTeclado.tecla_pressionada(i32 %TECLA_SETA_ESQUERDA)
  %2 = or i1 %0, %1
  br i1 %2, label %se, label %senao

pulo:                                             ; No predecessors!
  br label %saida

se:                                               ; preds = %incio_funcao
  %velocidade_horizontal = load double* @velocidade_horizontal
  %ACELERACAO_FOGUETE = load double* @ACELERACAO_FOGUETE
  %PERCENTUAL_VELOCIDADE_HORIZONTAL = load double* @PERCENTUAL_VELOCIDADE_HORIZONTAL
  %3 = fmul double %ACELERACAO_FOGUETE, %PERCENTUAL_VELOCIDADE_HORIZONTAL
  %4 = fsub double %velocidade_horizontal, %3
  store double %4, double* @velocidade_horizontal
  br label %saida

senao:                                            ; preds = %incio_funcao
  %velocidade_horizontal5 = load double* @velocidade_horizontal
  %5 = fcmp ult double %velocidade_horizontal5, 0.000000e+00
  br i1 %5, label %se2, label %senao3

saida:                                            ; preds = %pulo8, %se, %pulo
  br label %saida4

pulo1:                                            ; No predecessors!
  %TECLA_D = load i32* @TECLA_D
  %6 = call i1 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTeclado.tecla_pressionada(i32 %TECLA_D)
  %TECLA_SETA_DIREITA = load i32* @TECLA_SETA_DIREITA
  %7 = call i1 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTeclado.tecla_pressionada(i32 %TECLA_SETA_DIREITA)
  %8 = or i1 %6, %7
  br i1 %8, label %se9, label %senao10

se2:                                              ; preds = %senao
  br label %saida11

senao3:                                           ; preds = %senao
  %velocidade_horizontal6 = load double* @velocidade_horizontal
  %ACELERACAO_GRAVIDADE = load double* @ACELERACAO_GRAVIDADE
  %PERCENTUAL_VELOCIDADE_HORIZONTAL7 = load double* @PERCENTUAL_VELOCIDADE_HORIZONTAL
  %9 = fmul double %ACELERACAO_GRAVIDADE, %PERCENTUAL_VELOCIDADE_HORIZONTAL7
  %10 = fadd double %velocidade_horizontal6, %9
  store double %10, double* @velocidade_horizontal
  br label %saida4

saida4:                                           ; preds = %saida4, %senao3, %saida
  br label %saida4

pulo8:                                            ; No predecessors!
  br label %saida

se9:                                              ; preds = %pulo1
  %velocidade_horizontal12 = load double* @velocidade_horizontal
  %ACELERACAO_FOGUETE13 = load double* @ACELERACAO_FOGUETE
  %PERCENTUAL_VELOCIDADE_HORIZONTAL14 = load double* @PERCENTUAL_VELOCIDADE_HORIZONTAL
  %11 = fmul double %ACELERACAO_FOGUETE13, %PERCENTUAL_VELOCIDADE_HORIZONTAL14
  %12 = fadd double %velocidade_horizontal12, %11
  store double %12, double* @velocidade_horizontal
  br label %saida11

senao10:                                          ; preds = %pulo1
  %velocidade_horizontal19 = load double* @velocidade_horizontal
  %13 = fcmp ogt double %velocidade_horizontal19, 0.000000e+00
  br i1 %13, label %se16, label %senao17

saida11:                                          ; preds = %senao17, %se9, %se2
  br label %saida18

pulo15:                                           ; No predecessors!
  %velocidade_horizontal20 = load double* @velocidade_horizontal
  %ACELERACAO_GRAVIDADE21 = load double* @ACELERACAO_GRAVIDADE
  %PERCENTUAL_VELOCIDADE_HORIZONTAL22 = load double* @PERCENTUAL_VELOCIDADE_HORIZONTAL
  %14 = fmul double %ACELERACAO_GRAVIDADE21, %PERCENTUAL_VELOCIDADE_HORIZONTAL22
  %15 = fsub double %velocidade_horizontal20, %14
  store double %15, double* @velocidade_horizontal
  br label %saida18

se16:                                             ; preds = %senao10
  br label %saida18

senao17:                                          ; preds = %senao10
  br label %saida11

saida18:                                          ; preds = %se16, %pulo15, %saida11
}

define void @atualizar_posicao_foguete() {
incio_funcao:
  %x_foguete = load i32* @x_foguete
  %velocidade_horizontal = load double* @velocidade_horizontal
  %0 = call double @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaMatematica.arredondar(double %velocidade_horizontal, i32 1)
  %1 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTipos.real_para_inteiro(double %0)
  %2 = add i32 %x_foguete, %1
  store i32 %2, i32* @x_foguete
  %y_foguete = load i32* @y_foguete
  %velocidade_vertical = load double* @velocidade_vertical
  %3 = call double @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaMatematica.arredondar(double %velocidade_vertical, i32 1)
  %4 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTipos.real_para_inteiro(double %3)
  %5 = add i32 %y_foguete, %4
  store i32 %5, i32* @y_foguete
}

define void @atualizar_estado_foguete() {
incio_funcao:
  %x_foguete = load i32* @x_foguete
  %LARGURA_FOGUETE = load i32* @LARGURA_FOGUETE
  %0 = add i32 %x_foguete, %LARGURA_FOGUETE
  %1 = icmp ult i32 %0, 0
  %x_foguete1 = load i32* @x_foguete
  %LARGURA_TELA = load i32* @LARGURA_TELA
  %2 = icmp sgt i32 %x_foguete1, %LARGURA_TELA
  %3 = or i1 %1, %2
  %y_foguete = load i32* @y_foguete
  %ALTURA_FOGUETE = load i32* @ALTURA_FOGUETE
  %4 = add i32 %y_foguete, %ALTURA_FOGUETE
  %5 = icmp ult i32 %4, 0
  %6 = or i1 %3, %5
  br i1 %6, label %se, label %senao

pulo:                                             ; No predecessors!
  br label %saida

se:                                               ; preds = %incio_funcao
  store i1 true, i1* @foi_para_o_espaco
  br label %saida

senao:                                            ; preds = %incio_funcao
  %y_foguete6 = load i32* @y_foguete
  %ALTURA_FOGUETE7 = load i32* @ALTURA_FOGUETE
  %7 = add i32 %y_foguete6, %ALTURA_FOGUETE7
  %8 = sub i32 %7, 10
  %y_plataforma = load i32* @y_plataforma
  %9 = icmp sgt i32 %8, %y_plataforma
  br i1 %9, label %se3, label %senao4

saida:                                            ; preds = %saida5, %se, %pulo
  br label %saida5

pulo2:                                            ; No predecessors!
  %x_foguete12 = load i32* @x_foguete
  %x_plataforma = load i32* @x_plataforma
  %10 = icmp sgt i32 %x_foguete12, %x_plataforma
  %x_foguete13 = load i32* @x_foguete
  %x_plataforma14 = load i32* @x_plataforma
  %LARGURA_PLATAFORMA = load i32* @LARGURA_PLATAFORMA
  %11 = add i32 %x_plataforma14, %LARGURA_PLATAFORMA
  %LARGURA_FOGUETE15 = load i32* @LARGURA_FOGUETE
  %12 = sub i32 %11, %LARGURA_FOGUETE15
  %13 = icmp ult i32 %x_foguete13, %12
  %14 = and i1 %10, %13
  br i1 %14, label %se9, label %senao10

se3:                                              ; preds = %senao
  br label %saida11

senao4:                                           ; preds = %senao
  br label %saida5

saida5:                                           ; preds = %pulo16, %senao4, %saida
  br label %saida

pulo8:                                            ; No predecessors!
  %velocidade_vertical = load double* @velocidade_vertical
  %VELOCIDADE_MAXIMA_POUSO = load double* @VELOCIDADE_MAXIMA_POUSO
  %15 = fcmp ule double %velocidade_vertical, %VELOCIDADE_MAXIMA_POUSO
  br i1 %15, label %se17, label %senao18

se9:                                              ; preds = %pulo2
  br label %saida19

senao10:                                          ; preds = %pulo2
  %y_foguete24 = load i32* @y_foguete
  %ALTURA_FOGUETE25 = load i32* @ALTURA_FOGUETE
  %16 = add i32 %y_foguete24, %ALTURA_FOGUETE25
  %ALTURA_TELA = load i32* @ALTURA_TELA
  %17 = sub i32 %ALTURA_TELA, 57
  %x_foguete26 = load i32* @x_foguete
  %18 = uitofp i32 %x_foguete26 to double
  %19 = fsub double 4.000000e+02, %18
  %LARGURA_FOGUETE27 = load i32* @LARGURA_FOGUETE
  %20 = uitofp i32 %LARGURA_FOGUETE27 to double
  %21 = fdiv double %20, 2.000000e+00
  %22 = fadd double %19, %21
  %23 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaMatematica.valor_absoluto(double %22)
  %24 = uitofp i32 %23 to double
  %25 = fdiv double %24, 7.000000e+00
  %26 = uitofp i32 %17 to double
  %27 = fadd double %26, %25
  %28 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTipos.real_para_inteiro(double %27)
  %29 = icmp sgt i32 %16, %28
  br i1 %29, label %se21, label %senao22

saida11:                                          ; preds = %senao22, %saida19, %se3
  br label %saida23

pulo16:                                           ; No predecessors!
  br label %saida5

se17:                                             ; preds = %pulo8
  store i1 true, i1* @pousou
  %30 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaUtil.tempo_decorrido()
  %tempo_inicio_jogo = load i32* @tempo_inicio_jogo
  %31 = sub i32 %30, %tempo_inicio_jogo
  store i32 %31, i32* @tempo_total_jogo
  br label %saida19

senao18:                                          ; preds = %pulo8
  store i1 true, i1* @quebrou
  br label %saida19

saida19:                                          ; preds = %senao18, %se17, %se9
  br label %saida11

pulo20:                                           ; No predecessors!
  store i1 true, i1* @quebrou
  br label %saida23

se21:                                             ; preds = %senao10
  br label %saida23

senao22:                                          ; preds = %senao10
  br label %saida11

saida23:                                          ; preds = %se21, %pulo20, %saida11
}

define void @desenhar_fundo() {
incio_funcao:
  %indice_fundo = load i32* @indice_fundo
  %LARGURA_TELA = load i32* @LARGURA_TELA
  %0 = icmp sgt i32 %indice_fundo, %LARGURA_TELA
  br i1 %0, label %se, label %senao

pulo:                                             ; No predecessors!
  br label %saida

se:                                               ; preds = %incio_funcao
  %indice_fundo1 = load i32* @indice_fundo
  %LARGURA_TELA2 = load i32* @LARGURA_TELA
  %indice_fundo3 = load i32* @indice_fundo
  %LARGURA_TELA4 = load i32* @LARGURA_TELA
  %1 = sub i32 %indice_fundo3, %LARGURA_TELA4
  %2 = sub i32 %LARGURA_TELA2, %1
  %ALTURA_TELA = load i32* @ALTURA_TELA
  %imagem_fundo = load i32* @imagem_fundo
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_porcao_imagem(i32 0, i32 0, i32 %indice_fundo1, i32 0, i32 %2, i32 %ALTURA_TELA, i32 %imagem_fundo)
  %LARGURA_TELA5 = load i32* @LARGURA_TELA
  %indice_fundo6 = load i32* @indice_fundo
  %LARGURA_TELA7 = load i32* @LARGURA_TELA
  %3 = sub i32 %indice_fundo6, %LARGURA_TELA7
  %4 = sub i32 %LARGURA_TELA5, %3
  %LARGURA_TELA8 = load i32* @LARGURA_TELA
  %ALTURA_TELA9 = load i32* @ALTURA_TELA
  %imagem_fundo10 = load i32* @imagem_fundo
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_porcao_imagem(i32 %4, i32 0, i32 0, i32 0, i32 %LARGURA_TELA8, i32 %ALTURA_TELA9, i32 %imagem_fundo10)
  br label %saida

senao:                                            ; preds = %incio_funcao
  %indice_fundo11 = load i32* @indice_fundo
  %LARGURA_TELA12 = load i32* @LARGURA_TELA
  %ALTURA_TELA13 = load i32* @ALTURA_TELA
  %imagem_fundo14 = load i32* @imagem_fundo
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_porcao_imagem(i32 0, i32 0, i32 %indice_fundo11, i32 0, i32 %LARGURA_TELA12, i32 %ALTURA_TELA13, i32 %imagem_fundo14)
  br label %saida

saida:                                            ; preds = %senao, %se, %pulo
  %tempo_inicio = load i32* @tempo_inicio
  %ultimo_giro_fundo = load i32* @ultimo_giro_fundo
  %5 = sub i32 %tempo_inicio, %ultimo_giro_fundo
  %6 = icmp sgt i32 %5, 35
  br i1 %6, label %se16, label %senao17

pulo15:                                           ; No predecessors!
  br label %saida18

se16:                                             ; preds = %saida
  %indice_fundo19 = load i32* @indice_fundo
  %7 = add i32 %indice_fundo19, 1
  %LARGURA_TELA20 = load i32* @LARGURA_TELA
  %8 = mul i32 %LARGURA_TELA20, 2
  %9 = sdiv i32 %7, %8
  store i32 %9, i32* @indice_fundo
  %tempo_inicio21 = load i32* @tempo_inicio
  store i32 %tempo_inicio21, i32* @ultimo_giro_fundo
  br label %saida18

senao17:                                          ; preds = %saida
  br label %saida18

saida18:                                          ; preds = %senao17, %se16, %pulo15
}

define void @desenhar_planetas() {
incio_funcao:
  %indice_planetas = load i32* @indice_planetas
  %LARGURA_TELA = load i32* @LARGURA_TELA
  %0 = icmp sgt i32 %indice_planetas, %LARGURA_TELA
  br i1 %0, label %se, label %senao

pulo:                                             ; No predecessors!
  br label %saida

se:                                               ; preds = %incio_funcao
  %indice_planetas1 = load i32* @indice_planetas
  %LARGURA_TELA2 = load i32* @LARGURA_TELA
  %indice_planetas3 = load i32* @indice_planetas
  %LARGURA_TELA4 = load i32* @LARGURA_TELA
  %1 = sub i32 %indice_planetas3, %LARGURA_TELA4
  %2 = sub i32 %LARGURA_TELA2, %1
  %ALTURA_TELA = load i32* @ALTURA_TELA
  %imagem_planetas = load i32* @imagem_planetas
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_porcao_imagem(i32 0, i32 0, i32 %indice_planetas1, i32 0, i32 %2, i32 %ALTURA_TELA, i32 %imagem_planetas)
  %LARGURA_TELA5 = load i32* @LARGURA_TELA
  %indice_planetas6 = load i32* @indice_planetas
  %LARGURA_TELA7 = load i32* @LARGURA_TELA
  %3 = sub i32 %indice_planetas6, %LARGURA_TELA7
  %4 = sub i32 %LARGURA_TELA5, %3
  %LARGURA_TELA8 = load i32* @LARGURA_TELA
  %ALTURA_TELA9 = load i32* @ALTURA_TELA
  %imagem_planetas10 = load i32* @imagem_planetas
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_porcao_imagem(i32 %4, i32 0, i32 0, i32 0, i32 %LARGURA_TELA8, i32 %ALTURA_TELA9, i32 %imagem_planetas10)
  br label %saida

senao:                                            ; preds = %incio_funcao
  %indice_planetas11 = load i32* @indice_planetas
  %LARGURA_TELA12 = load i32* @LARGURA_TELA
  %ALTURA_TELA13 = load i32* @ALTURA_TELA
  %imagem_planetas14 = load i32* @imagem_planetas
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_porcao_imagem(i32 0, i32 0, i32 %indice_planetas11, i32 0, i32 %LARGURA_TELA12, i32 %ALTURA_TELA13, i32 %imagem_planetas14)
  br label %saida

saida:                                            ; preds = %senao, %se, %pulo
  %tempo_inicio = load i32* @tempo_inicio
  %ultimo_giro_planetas = load i32* @ultimo_giro_planetas
  %5 = sub i32 %tempo_inicio, %ultimo_giro_planetas
  %6 = icmp sgt i32 %5, 100
  br i1 %6, label %se16, label %senao17

pulo15:                                           ; No predecessors!
  br label %saida18

se16:                                             ; preds = %saida
  %indice_planetas19 = load i32* @indice_planetas
  %7 = add i32 %indice_planetas19, 1
  %LARGURA_TELA20 = load i32* @LARGURA_TELA
  %8 = mul i32 %LARGURA_TELA20, 2
  %9 = sdiv i32 %7, %8
  store i32 %9, i32* @indice_planetas
  %tempo_inicio21 = load i32* @tempo_inicio
  store i32 %tempo_inicio21, i32* @ultimo_giro_planetas
  br label %saida18

senao17:                                          ; preds = %saida
  br label %saida18

saida18:                                          ; preds = %senao17, %se16, %pulo15
}

define void @desenhar() {
incio_funcao:
  call void @desenhar_fundo()
  call void @desenhar_planetas()
  %ALTURA_TELA = load i32* @ALTURA_TELA
  %0 = sub i32 %ALTURA_TELA, 84
  %imagem_lua = load i32* @imagem_lua
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_imagem(i32 0, i32 %0, i32 %imagem_lua)
  %x_plataforma = load i32* @x_plataforma
  %y_plataforma = load i32* @y_plataforma
  %imagem_plataforma = load i32* @imagem_plataforma
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_imagem(i32 %x_plataforma, i32 %y_plataforma, i32 %imagem_plataforma)
  %pousou = load i1* @pousou
  br i1 %pousou, label %se, label %senao

pulo:                                             ; No predecessors!
  br label %saida

se:                                               ; preds = %incio_funcao
  call void @desenhar_sombra_foguete()
  %x_foguete = load i32* @x_foguete
  %y_foguete = load i32* @y_foguete
  %imagem_foguete = load i32* @imagem_foguete
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_imagem(i32 %x_foguete, i32 %y_foguete, i32 %imagem_foguete)
  br label %saida

senao:                                            ; preds = %incio_funcao
  %quebrou = load i1* @quebrou
  br i1 %quebrou, label %se2, label %senao3

saida:                                            ; preds = %pulo5, %se, %pulo
  br label %saida4

pulo1:                                            ; No predecessors!
  %1 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.renderizar()
  %tempo_inicio = load i32* @tempo_inicio
  %2 = sdiv i32 %tempo_inicio, 150
  %3 = icmp ult i32 %2, 75
  br i1 %3, label %se6, label %senao7

se2:                                              ; preds = %senao
  br label %saida8

senao3:                                           ; preds = %senao
  call void @desenhar_sombra_foguete()
  %acelerando = load i1* @acelerando
  br i1 %acelerando, label %se20, label %senao21

saida4:                                           ; preds = %saida22, %pulo9, %saida
  br label %saida22

pulo5:                                            ; No predecessors!
  br label %saida

se6:                                              ; preds = %pulo1
  %atualizou = load i1* @atualizou
  %4 = sub i1 false, %atualizou
  br i1 %4, label %se10, label %senao11

senao7:                                           ; preds = %pulo1
  br label %saida12

saida8:                                           ; preds = %saida12, %saida8, %se2
  store i1 false, i1* @atualizou
  br label %saida8

pulo9:                                            ; No predecessors!
  %x_foguete13 = load i32* @x_foguete
  %y_foguete14 = load i32* @y_foguete
  %ALTURA_FOGUETE = load i32* @ALTURA_FOGUETE
  %5 = add i32 %y_foguete14, %ALTURA_FOGUETE
  %6 = sub i32 %5, 43
  %imagem_foguete_quebrado = load i32* @imagem_foguete_quebrado
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_imagem(i32 %x_foguete13, i32 %6, i32 %imagem_foguete_quebrado)
  %x_foguete15 = load i32* @x_foguete
  %7 = add i32 %x_foguete15, 20
  %y_foguete16 = load i32* @y_foguete
  %ALTURA_FOGUETE17 = load i32* @ALTURA_FOGUETE
  %8 = add i32 %y_foguete16, %ALTURA_FOGUETE17
  %9 = sub i32 %8, 30
  %indice_fogo18 = load i32* @indice_fogo
  %10 = mul i32 %indice_fogo18, 30
  %imagem_fogo = load i32* @imagem_fogo
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_porcao_imagem(i32 %7, i32 %9, i32 %10, i32 0, i32 30, i32 45, i32 %imagem_fogo)
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.definir_cor(i32 16777215)
  br label %saida4

se10:                                             ; preds = %se6
  %indice_fogo = load i32* @indice_fogo
  %11 = add i32 %indice_fogo, 1
  %12 = sdiv i32 %11, 6
  store i32 %12, i32* @indice_fogo
  store i1 true, i1* @atualizou
  br label %saida12

senao11:                                          ; preds = %se6
  br label %saida12

saida12:                                          ; preds = %senao11, %se10, %senao7
  br label %saida8

pulo19:                                           ; No predecessors!
  %tempo_inicio27 = load i32* @tempo_inicio
  %13 = sdiv i32 %tempo_inicio27, 100
  %14 = icmp ult i32 %13, 50
  br i1 %14, label %se24, label %senao25

se20:                                             ; preds = %senao3
  br label %saida26

senao21:                                          ; preds = %senao3
  br label %saida22

saida22:                                          ; preds = %senao25, %senao21, %saida4
  %x_foguete32 = load i32* @x_foguete
  %y_foguete33 = load i32* @y_foguete
  %imagem_foguete34 = load i32* @imagem_foguete
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_imagem(i32 %x_foguete32, i32 %y_foguete33, i32 %imagem_foguete34)
  br label %saida4

pulo23:                                           ; No predecessors!
  %x_foguete28 = load i32* @x_foguete
  %15 = add i32 %x_foguete28, 10
  %y_foguete29 = load i32* @y_foguete
  %16 = add i32 %y_foguete29, 66
  %imagem_jato = load i32* @imagem_jato
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_imagem(i32 %15, i32 %16, i32 %imagem_jato)
  br label %saida26

se24:                                             ; preds = %pulo19
  %x_foguete30 = load i32* @x_foguete
  %17 = add i32 %x_foguete30, 10
  %y_foguete31 = load i32* @y_foguete
  %18 = add i32 %y_foguete31, 66
  %imagem_jato2 = load i32* @imagem_jato2
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_imagem(i32 %17, i32 %18, i32 %imagem_jato2)
  br label %saida26

senao25:                                          ; preds = %pulo19
  br label %saida22

saida26:                                          ; preds = %se24, %pulo23, %se20
}

define void @desenhar_sombra_foguete() {
incio_funcao:
  %x_foguete = load i32* @x_foguete
  %LARGURA_FOGUETE = load i32* @LARGURA_FOGUETE
  %0 = uitofp i32 %LARGURA_FOGUETE to double
  %1 = fdiv double %0, 2.000000e+00
  %2 = uitofp i32 %x_foguete to double
  %3 = fadd double %2, %1
  %x_centro_foguete = alloca i32
  %4 = fptoui double %3 to i32
  store i32 %4, i32* %x_centro_foguete
  %y_foguete = load i32* @y_foguete
  %ALTURA_FOGUETE = load i32* @ALTURA_FOGUETE
  %5 = add i32 %y_foguete, %ALTURA_FOGUETE
  %y_plataforma = load i32* @y_plataforma
  %6 = sub i32 %5, %y_plataforma
  %7 = sub i32 %6, 11
  %distancia_plataforma = alloca i32
  store i32 %7, i32* %distancia_plataforma
  %LARGURA_FOGUETE1 = load i32* @LARGURA_FOGUETE
  %distancia_plataforma2 = load i32* %distancia_plataforma
  %8 = uitofp i32 %distancia_plataforma2 to double
  %9 = fdiv double %8, 1.000000e+01
  %10 = uitofp i32 %LARGURA_FOGUETE1 to double
  %11 = fadd double %10, %9
  %largura_sombra = alloca i32
  %12 = fptoui double %11 to i32
  store i32 %12, i32* %largura_sombra
  %largura_sombra3 = load i32* %largura_sombra
  %13 = uitofp i32 %largura_sombra3 to double
  %14 = fdiv double %13, 1.000000e+01
  %altura_sombra = alloca i32
  %15 = fptoui double %14 to i32
  store i32 %15, i32* %altura_sombra
  %x_centro_foguete4 = load i32* %x_centro_foguete
  %largura_sombra5 = load i32* %largura_sombra
  %16 = uitofp i32 %largura_sombra5 to double
  %17 = fdiv double %16, 2.000000e+00
  %18 = uitofp i32 %x_centro_foguete4 to double
  %19 = fsub double %18, %17
  %x_sombra = alloca i32
  %20 = fptoui double %19 to i32
  store i32 %20, i32* %x_sombra
  %ALTURA_TELA = load i32* @ALTURA_TELA
  %21 = sub i32 %ALTURA_TELA, 57
  %x_centro_foguete6 = load i32* %x_centro_foguete
  %22 = uitofp i32 %x_centro_foguete6 to double
  %23 = fsub double 4.000000e+02, %22
  %24 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaMatematica.valor_absoluto(double %23)
  %25 = uitofp i32 %24 to double
  %26 = fdiv double %25, 7.000000e+00
  %27 = uitofp i32 %21 to double
  %28 = fadd double %27, %26
  %29 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaTipos.real_para_inteiro(double %28)
  %y_sombra = alloca i32
  store i32 %29, i32* %y_sombra
  %COR_PRETO = load i32* @COR_PRETO
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.definir_cor(i32 %COR_PRETO)
  %x_sombra7 = load i32* %x_sombra
  %y_sombra8 = load i32* %y_sombra
  %largura_sombra9 = load i32* %largura_sombra
  %altura_sombra10 = load i32* %altura_sombra
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_elipse(i32 %x_sombra7, i32 %y_sombra8, i32 %largura_sombra9, i32 %altura_sombra10, i1 true)
}

define void @reiniciar() {
incio_funcao:
  %0 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaUtil.sorteia(i32 10, i32 730)
  store i32 %0, i32* @x_foguete
  store i32 0, i32* @y_foguete
  store i1 false, i1* @acelerando
  store i1 false, i1* @pousou
  store i1 false, i1* @quebrou
  store i1 false, i1* @foi_para_o_espaco
  store double 0.000000e+00, double* @velocidade_vertical
  store double 0.000000e+00, double* @velocidade_horizontal
  %1 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaUtil.tempo_decorrido()
  store i32 %1, i32* @tempo_inicio_jogo
  store i32 0, i32* @tempo_total_jogo
}

define void @carregar_imagens() {
incio_funcao:
  %0 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.carregar_imagem(i8* getelementptr inbounds ([9 x i8]* @0, i32 0, i32 0))
  store i32 %0, i32* @imagem_lua
  %1 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.carregar_imagem(i8* getelementptr inbounds ([10 x i8]* @1, i32 0, i32 0))
  store i32 %1, i32* @imagem_fundo
  %2 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.carregar_imagem(i8* getelementptr inbounds ([13 x i8]* @2, i32 0, i32 0))
  store i32 %2, i32* @imagem_planetas
  %3 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.carregar_imagem(i8* getelementptr inbounds ([9 x i8]* @3, i32 0, i32 0))
  store i32 %3, i32* @imagem_menu
  %4 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.carregar_imagem(i8* getelementptr inbounds ([12 x i8]* @4, i32 0, i32 0))
  store i32 %4, i32* @imagem_foguete
  %5 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.carregar_imagem(i8* getelementptr inbounds ([18 x i8]* @5, i32 0, i32 0))
  store i32 %5, i32* @imagem_jato
  %6 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.carregar_imagem(i8* getelementptr inbounds ([21 x i8]* @6, i32 0, i32 0))
  store i32 %6, i32* @imagem_plataforma
  %7 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.carregar_imagem(i8* getelementptr inbounds ([18 x i8]* @7, i32 0, i32 0))
  store i32 %7, i32* @imagem_jato2
  %8 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.carregar_imagem(i8* getelementptr inbounds ([21 x i8]* @8, i32 0, i32 0))
  store i32 %8, i32* @imagem_foguete_quebrado
  %9 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.carregar_imagem(i8* getelementptr inbounds ([9 x i8]* @9, i32 0, i32 0))
  store i32 %9, i32* @imagem_fogo
}

define void @liberar_imagens() {
incio_funcao:
  %imagem_planetas = load i32* @imagem_planetas
  %0 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.liberar_imagem(i32 %imagem_planetas)
  %imagem_lua = load i32* @imagem_lua
  %1 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.liberar_imagem(i32 %imagem_lua)
  %imagem_fundo = load i32* @imagem_fundo
  %2 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.liberar_imagem(i32 %imagem_fundo)
  %imagem_menu = load i32* @imagem_menu
  %3 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.liberar_imagem(i32 %imagem_menu)
  %imagem_foguete = load i32* @imagem_foguete
  %4 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.liberar_imagem(i32 %imagem_foguete)
  %imagem_jato = load i32* @imagem_jato
  %5 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.liberar_imagem(i32 %imagem_jato)
  %imagem_plataforma = load i32* @imagem_plataforma
  %6 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.liberar_imagem(i32 %imagem_plataforma)
  %imagem_jato2 = load i32* @imagem_jato2
  %7 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.liberar_imagem(i32 %imagem_jato2)
  %imagem_foguete_quebrado = load i32* @imagem_foguete_quebrado
  %8 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.liberar_imagem(i32 %imagem_foguete_quebrado)
  %imagem_fogo = load i32* @imagem_fogo
  %9 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.liberar_imagem(i32 %imagem_fogo)
}

define void @inicializar() {
incio_funcao:
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.iniciar_modo_grafico(i1 true)
}

define void @finalizar() {
incio_funcao:
  call void @liberar_imagens()
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.encerrar_modo_grafico()
}

define void @inicio() {
incio_funcao:
  call void @carregar_imagens()
  call void @inicializar()
  call void @jogo()
  call void @finalizar()
}
