T = int(input())
findnum = ["0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G"]
for i in range(T):
    word = list(input().split())
    result = "" #1.최종 모든 정답을 넣을 문자열
    for j in word[1]: #입력받은 단어를 1개씩 꺼내 이진수 변환
        num = "" #2.1개의 문자의 이진수를 담을 문자열
        if j in findnum[:10]: #숫자면
            j = int(j)
            while j > 1: #j가 1이 될때까지 돌립니다
                num = str(j%2)+ num
                j = j//2
            num = str(j)+num #1 or 0 or 마지막 수를 넣기
            while len(num)<4:
                num = "0"+num
            result = result + num

        elif j in findnum[10:]: #영어면
            j = findnum.index(j)
            while j > 1: #j가 1이 될때까지 돌립니다
                num = str(j%2)+ num
                j = j//2
            num = str(j)+num #1 or 0 or 마지막 수를 넣기
            result = result + num
    print(f"#{i+1} {result}")