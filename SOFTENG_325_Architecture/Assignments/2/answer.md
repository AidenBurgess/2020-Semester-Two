# SOFTENG 325 Assignment 2

Name: Aiden Burgess

UPI: abur970

## Quality Attribute Scenarios

### 1. Availability

| Portion of Scenario | Concrete Value                         |
| ------------------- | -------------------------------------- |
| Source              | Random event                           |
| Stimulus            | Single processor failure               |
| Artifact            | Server                                 |
| Environment         | Normal working conditions              |
| Response            | Notify operator                        |
| Response Measure    | No loss of service (100% availability) |
|                     |                                        |

### 2. Security

| Portion of Scenario | Concrete Value                                |
| ------------------- | --------------------------------------------- |
| Source              | Unknown external source                       |
| Stimulus            | Unauthorized attempt to view exam information |
| Artifact            | Website                                       |
| Environment         | Online website                                |
| Response            | Refuse access to information                  |
| Response Measure    | Detect unauthorized access after 3 requests   |
|                     |                                               |

### 3. Usability

| Portion of Scenario | Concrete Value                                               |
| ------------------- | ------------------------------------------------------------ |
| Source              | Student                                                      |
| Stimulus            | Attempting login                                             |
| Artifact            | Login page of website                                        |
| Environment         | Online website at runtime                                    |
| Response            | Login page should make it clear where to input username and password |
| Response Measure    | #Successful Logins/#Attempts is 99%                          |
|                     |                                                              |

### 4. Performance

| Portion of Scenario | Concrete Value                                     |
| ------------------- | -------------------------------------------------- |
| Source              | Student                                            |
| Stimulus            | Watching live session                              |
| Artifact            | Server and Website                                 |
| Environment         | Normal working conditions                          |
| Response            | Stream video and audio to student                  |
| Response Measure    | Average latency of connection is less than 300msec |
|                     |                                                    |

### 5. Interoperability

| Portion of Scenario | Concrete Value                                          |
| ------------------- | ------------------------------------------------------- |
| Source              | UoA login service                                       |
| Stimulus            | User attempting login with university account           |
| Artifact            | Login system of server                                  |
| Environment         | System wishing to interoperate known prior to runtime   |
| Response            | Request is appropriately accepted and user is logged in |
| Response Measure    | Percentage of successful logins with UoA login service  |
|                     |                                                         |

## Architecture Design

3 tier architecture

Serverless for scalability

![image-20201101232030794](C:\Users\aiden\AppData\Roaming\Typora\typora-user-images\image-20201101232030794.png)



Router/Firewall for security

Separated system components for performance

