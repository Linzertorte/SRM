from collections import deque
class BracketExpressions:

    def ifPossible(self, expression):

        def is_valid(s):
            n = len(s)
            stack = deque()
            for i in s:
                if i==']':
                    if not stack or stack[0]!='[':
                        return False
                    stack.popleft()
                elif i==')':
                    if not stack or stack[0]!='(':
                        return False
                    stack.popleft()
                elif i=='}':
                    if not stack or stack[0]!='{':
                        return False
                    stack.popleft()
                else:
                    stack.appendleft(i)
            return len(stack)==0


        def dfs(i,e,expression):
            if i == len(expression):
                if is_valid(e):
                    return True
                else:
                    return False
            if expression[i]!='X':
                return dfs(i+1,e+expression[i],expression)
            else:
                for c in ['(',')','[',']','{','}']:
                    if dfs(i+1,e+c,expression):
                        return True
                return False

        result = dfs(0,"",expression)
        if result:
            return "possible"
        else:
            return "impossible"
