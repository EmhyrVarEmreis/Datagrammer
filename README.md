# Datagrammer
Simple command line app to show how Java's UDP Sockets work

Available commands
----
#### help
        Shows help
#### connect | c [host] [port]
        Connects socket to [host]:[port]
#### exit | e
        Exit program
#### init | i [host] [port]
        Bounds socket to [host]:[port]
#### packet size | ps [packetsize]
        Sets socket packet size to [packetsize] bytes
#### read packets | rp [count]
        Reads [count] packet and prints output
#### read single packet | rsp
        Reads single packet and prints output
#### read to timeout | rtt
        Reads all available packets and prints output
#### send | s [text]
        Sends [text]
#### timeout | t [timeout]
        Sets socket timeout to [timeout] ms
