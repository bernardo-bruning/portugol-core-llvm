; ModuleID = 'programa.bc'

@0 = private unnamed_addr constant [12 x i8] c"Acessou se!\00"
@1 = private unnamed_addr constant [15 x i8] c"Acessou senao!\00"

declare i32 @escreva(i8*, ...)

declare void @leia(i32, ...)

define void @inicio() {
incio_funcao:
  br i1 false, label %se, label %senao

pulo:                                             ; No predecessors!
  br label %saida

se:                                               ; preds = %incio_funcao
  %0 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([12 x i8]* @0, i32 0, i32 0))
  br label %saida

senao:                                            ; preds = %incio_funcao
  %1 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([15 x i8]* @1, i32 0, i32 0))
  br label %saida

saida:                                            ; preds = %senao, %se, %pulo
}