class RelativePath:
    def makeRelative(self, path, currentDir):
        path=path.split('/')
        currentDir=currentDir.split('/')
        if currentDir[-1]=="":
        	del currentDir[-1]
        up = len(currentDir)
        for i,f in enumerate(currentDir):
            if currentDir[i]!=path[i]:
                up = i
                break
        relative = '../'*(len(currentDir)-up)
        for i in xrange(up,len(path)):
            relative +=path[i]+'/'
	return relative[:-1]
