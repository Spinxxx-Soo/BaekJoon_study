n = int(input())
sumN = 0
for i in range(1,n+1):
    i = str(i)
    sumN = i.count('3') + i.count('6') + i.count('9')
    if sumN > 0:
        print('-'*sumN, end= ' ')
    else:
        print(i, end =' ')