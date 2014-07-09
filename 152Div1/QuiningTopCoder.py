class QuiningTopCoder:
    def testCode(self, source):
        def pop(stack):
            if len(stack)==0:
                return 0
            else:
                top = stack[-1]
                del stack[-1]
                return top
        output=""
        stack = []
        n = len(source)
        cycle = 0
        ip,d = 0,1
        while cycle < 80000:
            c = source[ip]
            if '0'<=c<='9':
                stack.append(int(c))
            elif c=='$':
                pop(stack)
            elif c==':':
                t = pop(stack)
                stack.append(t)
                stack.append(t)
            elif c=='W':
                a = pop(stack)
                b = pop(stack)
                stack.append(a)
                stack.append(b)
            elif c==',':
                x = pop(stack)
                output += source[abs(x)%n]
                if output not in source or source.index(output)!=0:
                    return "MISMATCH "+str(cycle)
                if len(output) == n:
                    return "QUINES "+str(cycle)
            elif c=='+':
                a = pop(stack)
                b = pop(stack)
                c = a+b
                if c<-1000000000 or c>1000000000:
                    return "OVERFLOW "+str(cycle)
                stack.append(c)
            elif c=='-':
                a = pop(stack)
                b = pop(stack)
                c = a-b
                if c<-1000000000 or c>1000000000:
                    return "OVERFLOW "+str(cycle)
                stack.append(c)
            elif c=='#':
                ip = (3*n+ip+2*d)%n
                cycle += 1
                continue
            elif c=='R':
                d *= -1
            elif c=='S':
                a = pop(stack)
                if a>0:
                    stack.append(1)
                else:
                    stack.append(-1)
            elif c=='_':
                a = pop(stack)
                d = a%n
            elif c=='J':
                x = pop(stack)
                ip = abs(x)%n
                cycle += 1
                continue
            elif c=='@':
                break
            ip = (3*n+ip+d)%n
            cycle += 1
        if cycle < 80000:
            return "BADEND "+str(cycle)
        return "TIMEOUT"

quine = QuiningTopCoder()
print quine.testCode("99WW+R1,#8+1")
