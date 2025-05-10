N, M = map(int, input().split(' '))
chess = []
result =[]
for i in range(N):
    color = input()
    chess.append(color)

for i in range(N-7): #N=세로
    for j in range(M-7) : #M=가로
        DrawStart_B = 0 #나는 시작이 'B' 입니다
        DrawStart_W = 0 #나는 시작이 'W' 입니다
        #8*8 검사칸 설정
        for a in range(i,i+8): #세로
            for b in range(j,j+8): #가로
                if (a+b)%2 == 0: #둘이 합쳐 짝수인 칸에는 항상 시작 색과 같아야함.
                    if chess[a][b] != 'B':
                        DrawStart_B += 1
                    if chess[a][b] != 'W':
                        DrawStart_W += 1
                else:
                    if chess[a][b] != 'W': #홀수인 칸에는 시작 색과 반대의 색이 들어가야함.
                        DrawStart_B += 1 #B로 시작인 홀수 칸에는 W가 들어가야함.
                    if chess[a][b] != 'B':
                        DrawStart_W += 1
        result.append(DrawStart_W) #하나의 검사칸이 끝나면 정답 리스트에 합산.
        result.append(DrawStart_B)

print(min(result))