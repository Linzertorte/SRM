class FolderSize:
    def calculateWaste(self, files, folderCount, clusterSize):
        wasted = [0]*folderCount
        for f in files:
            i, size = map(int,f.split(' '))
            if size%clusterSize!=0:
                a = clusterSize - size + (size/clusterSize)*clusterSize
                #print i,a
                wasted[i] += a
        return tuple(wasted)
