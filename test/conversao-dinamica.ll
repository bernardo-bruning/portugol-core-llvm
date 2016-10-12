; ModuleID = 'programa.bc'

@COR_PRETO = global i32 0

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

define void @dois_param(double, i32) {
incio_funcao:
}

define void @teste(double) {
incio_funcao:
  %t = alloca i32
  %1 = fptoui double %0 to i32
  store i32 %1, i32* %t
  %t1 = load i32* %t
  %2 = icmp sgt i32 %t1, 100
  br i1 %2, label %se, label %senao

condicao:                                         ; No predecessors!
  br label %saida

se:                                               ; preds = %incio_funcao
  call void @portugol.core.llvm.bibliotecas.portugol.core.llvm.bibliotecas.BibliotecaGraficos.desenhar_porcao_imagem(i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0)
  br label %saida

senao:                                            ; preds = %incio_funcao
  br label %saida

saida:                                            ; preds = %senao, %se, %condicao
  ret void
}

define void @inicio() {
incio_funcao:
  %numero = alloca i32
  store i32 2, i32* %numero
  %numero1 = load i32* %numero
  %0 = uitofp i32 %numero1 to double
  call void @teste(double %0)
  %numero2 = load i32* %numero
  %1 = uitofp i32 %numero2 to double
  %numero3 = load i32* %numero
  call void @dois_param(double %1, i32 %numero3)
  ret void
}
