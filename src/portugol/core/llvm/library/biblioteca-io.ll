@.escreva.int = private unnamed_addr constant [3 x i8] c"%d\00"

define i32 @escreva.cadeia(i8* ...) {
  %2 = call i32 (i8*, ...)* @printf(i8* %0)
  ret i32 1
}

define i32 @escreva.inteiro(i32 ...) {
  %2 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([3 x i8]* @.escreva.int, i32 0, i32 0), i32 %0)
  
}

declare i32 @printf(i8*, ...)