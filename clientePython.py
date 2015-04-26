# -*- coding: utf-8 -*-
"""
Created on Sun Jan 18 12:10:40 2015

@author: Rady
"""

import httplib
import urllib
import sys

headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "text/plain"}

# AÑADIR COCHE POR POST
def aniadeCoche(coche):
	params = urllib.urlencode(coche)
	print params
	conn = httplib.HTTPConnection("localhost:8080")
	conn.set_debuglevel(1)
	conn.request("POST", "/CarService/rest/coches", params, headers)
	conn.close()

# OBTENER TODOS LOS COCHES EN FORMATO XML POR GET
def getCoches():
	conn = httplib.HTTPConnection("localhost",8080)
	conn.request("GET","/CarService/rest/coches/")
	res = conn.getresponse()
	print res.status, res.reason
	print res.read()
	conn.close()

def fillDB():
	csvfile = open('TablaCoches.csv','rb')
	csvfile.readline()
	for lista in csvfile:
		row = lista[:-1].split(';')
		coche = {}
		coche['marca']      =row[0]
		coche['modelo']     =row[1]
		coche['tipo']       =row[2]
		coche['origen']     =row[3]
		coche['traccion']   =row[4]

		coche['precio']     =row[5]
		coche['cilindros']  =row[6]
		coche['caballos']   =row[7]
		coche['tamMotor']   =row[8]
		coche['peso']       =row[9]
		coche['longitud']   =row[10]

		aniadeCoche(coche)
	csvfile.close()

if __name__ == '__main__':
	fillDB()
	print 