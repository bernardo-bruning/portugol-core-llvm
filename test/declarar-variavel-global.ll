; ModuleID = 'programa.bc'

@numero_global = global i32 10
@real_global = global double 1.800000e-01

declare i32 @escreva(i8*, ...)

declare void @leia(i32, ...)

define void @inicio() {
incio_funcao:
  %numero_global = load i32* @numero_global
  %0 = add i32 10, %numero_global
  %numero = alloca i32
  store i32 %0, i32* %numero
  %numero_global1 = load i32* @numero_global
  %1 = add i32 %numero_global1, 10
  store i32 %1, i32* @numero_global
  call void @segunda_operacao()
  ret void
}

define void @segunda_operacao() {
incio_funcao:
  %numero = alloca i32
  store i32 4, i32* %numero
  %numero_global = load i32* @numero_global
  %numero1 = load i32* %numero
  %0 = add i32 %numero_global, %numero1
  store i32 %0, i32* @numero_global
  ret void
}
