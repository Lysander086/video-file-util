# not tested !!!
import os
import datetime
import sys
import argparse
from moviepy.editor import VideoFileClip

if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description='Compute Total Time of a Series of Videos')
    parser.add_argument("--path", metavar="PATH", default=".",
                        help="the root path of the videos(default: .)")
    parser.add_argument("--type", metavar="TYPE", default=".mkv",
                        help="the type of the videos(default: .mkv)")
    args = parser.parse_args()
    filelist = []
    for a, b, c in os.walk(args.path):
        for name in c:
            fname = os.path.join(a, name)
            if fname.endswith(args.type):
                filelist.append(fname)
    ftime = 0.0
    for item in filelist:
        clip = VideoFileClip(item)
        ftime += clip.duration
    print("%d seconds: " % ftime,str(datetime.timedelta(seconds=ftime)))