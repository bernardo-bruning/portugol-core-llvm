; ModuleID = 'programa'

declare i32 @escreva(i8*, ...)

declare i32 @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_criar_cor(i32, i32, i32)

declare i32 @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_definir_cor(i32)

declare i32 @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_desenhar_ponto(i32, i32)

define i32 @main(i32) {
entry:
  %1 = call i32 @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_criar_cor(i32 20, i32 20, i32 20)
  %cor = alloca i32
  store i32 %1, i32* %cor
  %cor.carregado = load i32* %cor
  %2 = call i32 @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_definir_cor(i32 %cor.carregado)
  %3 = call i32 @portugol_core_llvm_bibliotecas_portugol_core_llvm_bibliotecas_BibliotecaGraficos_desenhar_ponto(i32 10, i32 10)
  ret i32 1
}