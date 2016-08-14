; ModuleID = 'programa'
define i32 @main(i32) {
entry:
  %1 = mul i32 1, 45
  %2 = udiv i32 %1, 5
  %numero = sub i32 1, %2
  call i32 @escreva_int(i32 %numero)
  ret i32 1
}
