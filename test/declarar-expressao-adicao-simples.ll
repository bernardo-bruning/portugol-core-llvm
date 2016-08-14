; ModuleID = 'programa'
define i32 @main(i32) {
entry:
  %numero = add i32 1, 1
  call i32 @escreva(i32 %1)
  ret i32 1
}
