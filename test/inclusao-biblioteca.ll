; ModuleID = 'programa'

declare i32 @escreva(i8*, ...)

declare i32 @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_carregar_imagem(i8*)

declare i32 @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_criar_cor(i32, i32, i32)

declare void @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_definir_cor(i32)

declare void @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_desenhar_ponto(i32, i32)

declare void @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_desenhar_imagem(i32, i32, i32)

declare void @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_renderizar_imagem(i32, i32)

declare i32 @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_renderizar()

declare i32 @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_liberar_imagem(i32)

declare void @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_iniciar_modo_grafico(i1)

declare void @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_encerrar_modo_grafico()

define i32 @main(i32) {
entry:
  call void @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_iniciar_modo_grafico(i1 false)
  %1 = call i32 @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_criar_cor(i32 20, i32 20, i32 20)
  %cor = alloca i32
  store i32 %1, i32* %cor
  %cor.carregado = load i32* %cor
  call void @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_definir_cor(i32 %cor.carregado)
  call void @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_desenhar_ponto(i32 10, i32 10)
  call void @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_encerrar_modo_grafico()
  ret i32 0
}