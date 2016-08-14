@.escreva.int = private unnamed_addr constant [3 x i8] c"%d\00"

define i32 @escreva(i32) {
  %2 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([3 x i8]* @.escreva.int, i32 0, i32 0), i32 %0)
  ret i32 1
}

declare i32 @printf(i8*, ...)