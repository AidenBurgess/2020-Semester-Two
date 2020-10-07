#!/usr/bin/env python

# Name: Aiden Burgess
# Login: abur970

from __future__ import print_function, absolute_import, division

import logging

import os
import sys
from errno import ENOENT
from itertools import chain

from fuse import FUSE, FuseOSError, Operations, LoggingMixIn
from passthrough import Passthrough
from memory import Memory

class A2Fuse2(LoggingMixIn):
    def __init__(self, source1, source2):
        self.pass1 = Passthrough(source1)
        self.pass2 = Passthrough(source2)
        self.mem = Memory()

    def get_method(self, _class, name):
        return getattr(_class, name)

    def all_src(self, func, *args):
        for src in [self.pass1, self.pass2, self.mem]:
            try:
                return self.get_method(src, func)(*args)
            except:
                pass
        else:
            raise FuseOSError(ENOENT)

    def getattr(self, *args):
        return self.all_src("getattr", *args)

    def readdir(self, *args):
        return chain(self.pass1.readdir(*args), self.pass2.readdir(*args), self.mem.readdir(*args))

    def open(self, *args):
        return self.all_src("open", *args)

    def create(self, path, mode):
        return self.mem.create(path, mode)

    def write(self, path, data, offset, fh):
        data = "".join(map(chr, data))
        return self.all_src("write", path, data, offset, fh)

    def read(self, path, size, offset, fh):
        res = self.all_src("read", path, size, offset, fh)
        return res if type(res) is bytes else res.encode()
    
    def unlink(self, path):
        return self.all_src("unlink", path)

    def chown(self, *args):
        return self.all_src("chown", *args)

def main(source1, source2, mount):
    FUSE(A2Fuse2(source1, source2), mount, nothreads=True, foreground=True)

if __name__ == '__main__':
    logging.basicConfig(level=logging.DEBUG)
    main(sys.argv[1], sys.argv[2], sys.argv[3])
