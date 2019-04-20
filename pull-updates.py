from os import system
from datetime import datetime

confirm = str(input("Confirm download of new version? [Y/N]")).lower()

if confirm == "y":
    system("git pull origin master")
    print("\ndone\n")
    system("pause")
