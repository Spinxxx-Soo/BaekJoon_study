from collections import deque
n,m = map(int, input().split())
index1 = list(map(int, input().split())) #뽑으려는 인덱스 받기

data = deque([i for i in range(1,n+1)]) #[1,2,3,4,5,6,7,8,9,10] =deque
cnt =0
for num in index1:
    while 1:
        if data[0]==num:
            data.popleft() #제일 왼쪽 요소 꺼내기 #pop은 제일 오른쪽 삭제
            break
        else:
            if data.index(num) <= len(data)//2: #뽑으려는 수가 절반기준 왼쪽에 있으면
                data.rotate(-1) #제일 왼쪽것이 제일 오른쪽 끝으로
                cnt += 1 #횟수 저장
            else:
                data.rotate(1)  #제일 오른쪽 것이 제일 왼쪽 끝으로
                cnt +=1 #저장

print(cnt)