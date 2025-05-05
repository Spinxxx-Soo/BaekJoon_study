N = int(input())
Fibonacci = [0]*N

def F(n):
  if n < 2 :
    return N
  if n >= 2:
    Fibonacci[0],Fibonacci[1] = 1,1
    for i in range(2,n):
      Fibonacci[i] = Fibonacci[i-2] + Fibonacci[i-1]
    return Fibonacci[n-1]

print(F(N))