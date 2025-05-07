#1316 그룹 단어 체커

def checker(w): #문자열이 들어오면 연속문자인지 쳌
    visited = []
    i = 0
    find = '' #if) w=aabba / w=aaabb
    for i in w: # i= a
        if i != find and i in visited: #i= a, find =b, visited =[a, b]
            return 0 #만약 아니면 중도 0 리턴
        find = i # find = a
        visited.append(i) #visited = [a]
    return 1 # 루프가 다 끝나야 리턴..?

n = int(input())
cnt =0
for i in range(n):
    word = input()
    N = checker(word)
    if N ==1:
        cnt += 1

print(cnt)