T = int(input())

for i in range(1,T+1):
    money = int(input()) #137,850원
    result = []
    # // -> 찾는 단위, % -> 이후 남은 애들
    result.append(money//50000) #0.5만원 갯수
    money = money%100000 #5만원 중복 제거
    if result[0] != 0 and money-50000 >=0: #5만원 중복 제거
        money = money-50000
    result.append(money//10000) #1.1만원 갯수
  
    money = money%10000 #7,850원
    result.append(money//5000) #2.5천원 갯수
    if result[2] != 0 and money-5000 >=0: #5천원 중복 제거
        money = money-5000 
    result.append(money//1000) #3.1천원 갯수
    
    money = money%1000 #850원
    result.append(money//500) #4.500원 갯수
    if result[4] != 0 and money-500 >=0: #500원 중복 제거
        money = money-500
    result.append(money//100) #5.100원 갯수
     
    money = money%100 #50원
    result.append(money//50) #6.50원 갯수
    if result[6] != 0 and money-50 >=0: #50원 중복 제거
        money = money-50
    result.append(money//10) #7.10원 갯수

    print(f'#{i}')
    print(*result)