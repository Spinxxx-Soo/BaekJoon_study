def recursion(s, l, r, n=1):
    if l >= r: return 1, n
    elif s[l] != s[r]: return 0, n
    else: 
        return recursion(s, l+1, r-1 ,n+1)

def isPalindrome(s):
    return recursion(s, 0, len(s)-1)

n = int(input())
word_list =[]

for i in range(n):
    word = input()
    W = list(isPalindrome(word))
    word_list.append(W)
for w in word_list:
    print(*w)