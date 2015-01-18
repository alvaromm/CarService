# -*- coding: utf-8 -*-
"""
Created on Sun Jan 18 12:10:40 2015

@author: Rady
"""

import httplib
import urllib
import json
import csv
import sys

#headers = {"Content-type": "text/html", "Accept": "text/plain"}
headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "text/plain"}

coche = {}
coche['marca']      ='Renault'
coche['modelo']     ='Megane'
coche['tipo']       ='Turismo'
coche['origen']     ='Francia'
coche['traccion']   ='Delantera'

coche['precio']     ='1000'
coche['cilindros']  ='4'
coche['caballos']   ='140'
coche['tamMotor']   ='457'
coche['peso']       ='145'
coche['longitud']   ='123'



# POST AÑADIR COCHE

params = urllib.urlencode(coche)
print params
conn = httplib.HTTPConnection("localhost:8080")
#conn.set_debuglevel(1)
conn.request("POST", "/CarService/rest/coches", params, headers)
response = conn.getresponse()
print response.status, response.reason
#print response.read()
conn.close()


# GET LEER coches
conn = httplib.HTTPConnection("localhost",8080)
conn.request("GET","/CarService/rest/coches/")
res = conn.getresponse()
print res.status, res.reason
print res.read()
conn.close()