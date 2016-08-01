/* 
 * File 	: ./scripts/prepare-mongo/serv-picture.js
 * Author(s)	: Zidmann
 * Function 	: This file determines which data to put inside the Picture mongo database for Unit Test
 * Version  	: 1.0.0
 * Note		: To run the script : mongo Service_Picture serv-picture.js
 */

// Define the token for the local tests
db.tokens.remove({});
db.tokens.insert({ source : "JunitTests",   hash : "sha512$44dfa8990f$5$2428e5d0c834838cf9378836b100e5e4ab0598b18e9f4244c9121774947b80fd4971a3f914dc1b5fa9259d0464c260ee31888b8b7d7135cddb52ed0107bfe4c8", created : new Date() });
db.tokens.insert({ source : "JunitTests_1", hash : "sha512$087431b5db$5$70d260b798f6d8b1c98bb4a0c234636ac3299816d3e45cf50b2ee60e387c4988cade2e3e02896ecc02216e518a53c967d3a796c15e5912ba9c782823759d933e", created : new Date() });
db.tokens.insert({ source : "JunitTests_2", hash : "sha512$e9d80687d9$5$0e0cdbe98d348420ff5840f13077d3499ad11f8e3e4857df93926339473e0cdd2a8cd4674fa0200590d40606d29045c1faf1306f2e6ebf9192acf37cecc48f5e", created : new Date() });
db.tokens.insert({ source : "JunitTests_3", hash : "sha512$663bc84132$5$4367fa804bfedf5b63bb655181bf7bf6362017e8adcdca73fee2fe61174f3192ae7ed50a38befc4e64c46534cfb1575fc0080a710afab1eca09b66595c7e36ae", created : new Date() });

