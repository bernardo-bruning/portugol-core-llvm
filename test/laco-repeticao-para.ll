; ModuleID = 'programa.bc'

@0 = private unnamed_addr constant [7 x i8] c"Teste\0A\00"

declare i32 @escreva(i8*, ...)

declare void @leia(i32, ...)

define void @inicio() {
incio_funcao:
  %contador = alloca i32
  store i32 1, i32* %contador
  br label %para.entrada

para.condicao:                                    ; preds = %para.entrada
  %contador1 = load i32* %contador
  %0 = icmp ule i32 %contador1, 10
  br i1 %0, label %para.entrada, label %para.saida

para.entrada:                                     ; preds = %para.condicao, %incio_funcao
  %1 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([7 x i8]* @0, i32 0, i32 0))
  %contador2 = load i32* %contador
  %2 = add i32 %contador2, 1
  store i32 %2, i32* %contador
  br label %para.condicao

para.saida:                                       ; preds = %para.condicao
  ret void
}