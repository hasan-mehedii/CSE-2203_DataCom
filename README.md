# CSE-2203 Data and Telecommunication

## Overview
This repository, maintained by Mehedi Hasan, contains resources for the **CSE-2203 Data Communication** course, including lab exercises, assignments, and documentation. The `Labs/` folder includes PDF files and code for lab experiments focusing on key data communication concepts such as signal encoding, bit stuffing, multiplexing, error detection, and physical layer media.

## Repository Structure
- **`Labs/`**: Contains PDF files (e.g., `Lab1.pdf`, `Lab2.pdf`, etc.) and code for lab experiments.
- **`Docs/`**: Course notes or reference materials.
- **`Scripts/`**: Utility scripts for simulations or lab tasks.


## Lab Works and Theoretical Background
Below is a summary of the lab works, each assumed to be documented in a PDF file in the `Labs/` folder. Each lab’s objective and theoretical background are provided based on the specified topics.

### Lab 1: NRZ-I, NRZ-L, and Manchester Encoding and Decoding
**Objective**: Implement and analyze Non-Return-to-Zero Inverted (NRZ-I), Non-Return-to-Zero Level (NRZ-L), and Manchester encoding/decoding schemes.  
**Theory**:  
Signal encoding converts digital data (0s and 1s) into signals for transmission over a medium. Key concepts include:
- **NRZ-L (Non-Return-to-Zero Level)**: Represents 0 and 1 with distinct voltage levels (e.g., high for 1, low for 0). Simple but sensitive to baseline wander.
- **NRZ-I (Non-Return-to-Zero Inverted)**: Transitions signal for 1, no change for 0. Reduces errors in long sequences of 1s.
- **Manchester Encoding**: Combines data and clock by transitioning mid-bit (high-to-low for 0, low-to-high for 1). Self-clocking but uses more bandwidth.  
**Activities**: Write code (e.g., in Python) to encode/decode a bit stream using NRZ-L, NRZ-I, and Manchester schemes and visualize signal patterns.

### Lab 2: AMI, Pseudoternary, and MLT-3 Encoding and Decoding
**Objective**: Implement Alternate Mark Inversion (AMI), Pseudoternary, and Multilevel Threshold-3 (MLT-3) encoding/decoding schemes.  
**Theory**:  
These encoding schemes use multiple voltage levels to improve efficiency or reduce errors. Key concepts include:
- **AMI (Alternate Mark Inversion)**: Uses 0V for 0, alternating positive/negative voltages for 1s. Ensures zero DC bias but requires synchronization.
- **Pseudoternary**: Inverts AMI (0s alternate voltages, 1 is 0V). Reduces bandwidth needs.
- **MLT-3**: Cycles through three voltage levels (+V, 0, -V) with transitions for 1s. Used in Ethernet for reduced signal frequency.  
**Activities**: Simulate encoding/decoding in Python/C++ and compare bandwidth efficiency.

### Lab 3: Implementing Bit Stuffing and De-stuffing Using Socket Programming
**Objective**: Implement bit stuffing and de-stuffing in a client-server application using socket programming.  
**Theory**:  
Bit stuffing ensures data transparency in frame-based protocols by inserting extra bits to prevent flag sequence misinterpretation. Key concepts include:
- **Bit Stuffing**: In a bit stream, after five consecutive 1s, insert a 0 to distinguish data from frame delimiters (e.g., 01111110).
- **De-stuffing**: Removes inserted 0s at the receiver to recover original data.
- **Socket Programming**: Uses TCP/UDP sockets to transmit stuffed bit streams between client and server.  
**Activities**: Develop a Python program using sockets to stuff a bit stream, transmit it, and de-stuff at the receiver.

### Lab 4: Multiplexing and Demultiplexing
**Objective**: Simulate multiplexing and demultiplexing techniques for efficient channel use.  
**Theory**:  
Multiplexing combines multiple signals into one medium; demultiplexing separates them. Key concepts include:
- **Frequency Division Multiplexing (FDM)**: Allocates different frequency bands to signals.
- **Time Division Multiplexing (TDM)**: Divides time slots for each signal.
- **Demultiplexing**: Reconstructs original signals at the receiver using identifiers or timing.  
**Activities**: Simulate TDM/FDM in Python or a network simulator, combining/separating sample data streams.

### Lab 5: CRC and Error Detection
**Objective**: Implement Cyclic Redundancy Check (CRC) for error detection in data transmission.  
**Theory**:  
Error detection ensures data integrity during transmission. Key concepts include:
- **CRC (Cyclic Redundancy Check)**: Uses polynomial division to generate a checksum appended to data. Receiver verifies by recomputing.
- **Error Detection**: CRC detects burst errors up to the degree of the polynomial.
- **Implementation**: Divides data by a generator polynomial (e.g., CRC-16) to compute remainder.  
**Activities**: Code a CRC algorithm in Python/C++ to generate and verify checksums for sample data.

### Lab 6: UTP and Optical Fiber Cable
**Objective**: Study Unshielded Twisted Pair (UTP) and optical fiber cables for data transmission.  
**Theory**:  
Physical layer media determine transmission speed and reliability. Key concepts include:
- **UTP (Unshielded Twisted Pair)**: Twisted copper wires (e.g., Cat5, Cat6) reduce interference. Used in Ethernet, limited by distance (~100m).
- **Optical Fiber**: Transmits data as light pulses, offering high bandwidth, long distances, and noise immunity.
- **Comparison**: UTP is cheaper but slower; fiber is faster but costlier.  
**Activities**: Analyze cable properties (e.g., bandwidth, attenuation) via simulation or lab measurements.

### Lab 7: Spreading
**Objective**: Implement and analyze spreading techniques like DSSS or FHSS.  
**Theory**:  
Spread spectrum techniques enhance signal reliability and security. Key concepts include:
- **Direct Sequence Spread Spectrum (DSSS)**: Spreads signal using a pseudo-random code, improving noise resistance.
- **Frequency Hopping Spread Spectrum (FHSS)**: Rapidly switches frequencies to avoid interference.
- **Applications**: Used in Wi-Fi, Bluetooth, and military communications.  
**Activities**: Simulate DSSS/FHSS in Python or a network simulator to study signal spreading and recovery.

## Prerequisites
To work with the lab exercises:
- **Programming Languages**: Python, Java, or others specified in lab PDFs.


## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/hasan-mehedii/CSE-2203_DataCom.git
   ```
2. Navigate to the repository:
   ```bash
   cd CSE-2203_DataCom
   ```
3. Check individual lab folders for specific setup instructions.


## Contributing
This repository is primarily for coursework. To contribute:
1. Fork the repository.
2. Create a feature branch (`git checkout -b lab-update`).
3. Commit changes (`git commit -m "Update Lab 5 CRC code"`).
4. Push to the branch (`git push origin lab-update`).
5. Create a pull request.  
Consult with the repository owner or course instructor before contributing.

## License
This repository is for educational purposes. Contact the repository owner (hasan-mehedii) for usage permissions.

## Contact
For questions, reach out to [hasan-mehedii](https://github.com/hasan-mehedii) via GitHub.
