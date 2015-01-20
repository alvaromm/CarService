# -*- coding: utf-8 -*-
"""
Created on Sun Jan 18 12:10:40 2015

@author: Rady
"""

import httplib
import urllib
import csv
import sys

headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "text/plain"}

# AÑADIR COCHE POR POST
def aniadeCoche(coche):
	params = urllib.urlencode(coche)
	print params
	conn = httplib.HTTPConnection("localhost:8080")
	#conn.set_debuglevel(1)
	conn.request("POST", "/CarService/rest/coches", params, headers)
	#response = conn.getresponse()
	#print response.status, response.reason
	#print response.read()
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
	with open('TablaCoches.csv', 'rb') as csvfile:
		reader = csv.DictReader(csvfile, delimiter=";")
		for row in reader:
			coche = {}
			coche['marca']      =row['Marca']
			coche['modelo']     =row['Modelo']
			coche['tipo']       =row['Tipo']
			coche['origen']     =row['Origen']
			coche['traccion']   =row['Traccion']

			coche['precio']     =row['Precio']
			coche['cilindros']  =row['Cilindros']
			coche['caballos']   =row['Caballos']
			coche['tamMotor']   =row['TamMotor']
			coche['peso']       =row['Peso']
			coche['longitud']   =row['Longitud']

			print coche
			aniadeCoche(coche)

if __name__ == '__main__':
	fillDB()