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

define void @inicio() {
incio_funcao:
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.iniciar_modo_grafico(i1 false)
  %0 = call i32 @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.criar_cor(i32 20, i32 20, i32 20)
  %cor = alloca i32
  store i32 %0, i32* %cor
  %cor1 = load i32* %cor
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.definir_cor(i32 %cor1)
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_ponto(i32 10, i32 10)
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.encerrar_modo_grafico()
  ret void
}
