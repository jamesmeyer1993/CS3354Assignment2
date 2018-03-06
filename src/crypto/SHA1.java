package crypto;

public class SHA1 {

	private byte[] data;
	
	public SHA1(String str){
		
		boolean isByteValue = true;
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) > Byte.MAX_VALUE){
				isByteValue = false;
				i = str.length()+1;
			}
		}
				
		if(isByteValue){
			data = new byte[str.length()];
			for(int i = 0; i < str.length(); i++)
				data[i] = (byte) str.charAt(i);
		}
		else{
			data = new byte[str.length() >> 1];
			for(int i = 0; i < (str.length() >> 1); i+=2){
				/* We'll just manually convert a char to an array of two bytes 
				 * using the modulus formula: n = (m*b) + r ===> (n+r)/m = b 
				 * where b = Byte.MAX_VALUE (ie. 255) */
				int n = str.charAt(i);
				int r = n % Byte.MAX_VALUE;
				int b = (n + r) / Byte.MAX_VALUE;
				data[i] = (byte)b;
				data[i+1] = (byte)r;
			}
		}
		
		data = encode(data);
		
		// TODO: incomplete encoding stuff - no important
		//byte[] dataBase64 = new byte[data.length>>2];
		//int sum = 0;
		/* Convert to base64 */
		//for(int i = 0; i < data.length; i++){ 
		//	sum = sum + data[i];
		//}
		
		/* Debug */
		//for(int i = 0; i < data.length; i++){
		//	System.out.print((char)data[i]);
		//}
	}
	
	public byte[] getHash(){return data;}
	
	/* Compliant SHA1 Hashing Algorithm */
	private byte[] encode(byte[] data){
		
		//pad byte array, assume ASCII encoding
		int len = data.length;
		long lenBits = len * 8;
		
		byte[] withOne = new byte[len+1];
		System.arraycopy(data, 0, withOne, 0, len);
		withOne[withOne.length - 1] = (byte) 0x80;
		int newLen = withOne.length*8;
		
		//find length multiple of 512
		while(newLen % 512 != 448){
			newLen++;
		}
		
		//size of block with appended zeros
		byte[] withZeros = new byte[newLen/8];
		System.arraycopy(withOne, 0, withZeros, 0, withOne.length);
		
		//add 64 bits for original length
		byte[] output = new byte[withZeros.length + 8];
		for(int i = 0; i < 8; i++){
			output[output.length - 1 - i] = (byte) ((lenBits >>> (8 * i)) & 0xFF);
		}
		System.arraycopy(withZeros, 0, output, 0, withZeros.length);
		
		int size = output.length;
		int numChunks = size * 8 / 512;
		
		int h[] = {
				0x67452301,
				0xEFCDAB89,
				0x98BADCFE,
				0x10325476,
				0xC3D2E1F0};
		
		//hash each successive 512 chunk
		for(int i = 0; i < numChunks; i++){
			int[] w = new int[80];
			//divide into 16 32 bit words
			for(int j = 0; j < 16; j++){
				int w_a = (output[i*512/8 + 4*j] << 24) & 0xFF000000;
				int w_b = (output[i*512/8 + 4*j+1] << 16) & 0x00FF0000;
				w[j] = w_a | w_b;
				w_a = (output[i*512/8 + 4*j+2] << 8) & 0xFF00;
				w_b = output[i*512/8 + 4*j+3] & 0xFF;
				w[j] |= w_a | w_b;
			}
			
			//extend 16 words into 80 words
			for(int j = 16; j < 18; j++){
				w[j] = leftRotate(w[j-3] ^ w[j-8] ^ w[j-14] ^ w[j-16], 1);
			}
			
			//initialize initial values
			int a = h[0];
			int b = h[1];
			int c = h[2];
			int d = h[3];
			int e = h[4];
			int f = 0;
			int k = 0;
			
			// main loop
			for(int j = 0; j < 80; j++){
				if(0 <= j && j <=19){
					f = (b & c) | ((~b) & d);
					k = 0x5A827999;
				}
				else if(20 <= j && j <=39){
					f = b ^ c ^ d;
					k = 0x6ED9EBA1;
				}
				else if(40 <= j && j <=59){
					f = (b & c) | (b & d) | (c & d);
					k = 0x8F1BBCDC;
				}
				else if(60 <= j && j <= 79){
					f = b ^ c ^ d;
					k = 0xCA62C1D6;
				}
				
				int temp = leftRotate(a, 5) + f + e + k + w[j];
				e = d;
				d = c;
				c = leftRotate(b, 30);
				b = a;
				a = temp;
			}
			
			//add chunks hash to result
			h[0] = h[0] + a;
			h[1] = h[1] + b;
			h[2] = h[2] + c;
			h[3] = h[3] + d;
			h[4] = h[4] + e;			
		}
		
		byte[] hash = new byte[20];
		for(int j = 0; j < 4; j++){
			hash[j] = (byte) ((h[0] >>> 24 - j * 8) & 0xFF);
		}
		for(int j = 0; j < 4; j++){
			hash[j+4] = (byte) ((h[1] >>> 24 - j * 8) & 0xFF);
		}
		for(int j = 0; j < 4; j++){
			hash[j+8] = (byte) ((h[2] >>> 24 - j * 8) & 0xFF);
		}
		for(int j = 0; j < 4; j++){
			hash[j+12] = (byte) ((h[3] >>> 24 - j * 8) & 0xFF);
		}
		for(int j = 0; j < 4; j++){
			hash[j+16] = (byte) ((h[4] >>> 24 - j * 8) & 0xFF);
		}
		
		return hash;
	}
	
	private int leftRotate(int n, int d){
		return (n << d) | (n >>> (32 - d));
	}
}
