# FOCUS DATA MCP Server [[中文](./README_CN.md)]

A Model Context Protocol (MCP) server enables artificial intelligence assistants to directly query data results. Users
obtain data results in natural language.

## Features

- Select Datafocus data table initialization dialogue
- Natural language data acquisition results

## Prerequisites

- jdk 23 or higher. Download [jdk](https://www.oracle.com/java/technologies/downloads/)
- gradle 8.12 or higher. Download [gradle](https://gradle.org/install/)
- register [Datafocus](https://www.datafocus.ai/) to obtain bearer token:
  1. Register an account in [Datafocus](https://www.datafocus.ai/)
  2. Create an application
    3. Enter the application
    4. Admin -> Interface authentication -> Bearer Token -> New Bearer Token
       ![bearer token](bearer_token.png)
  
## Installation

1. Clone this repository:

```bash
git clone https://github.com/FocusSearch/focus_mcp_data.git
cd focus_mcp_data
```

2. Build the server:

```bash
gradle clean
gradle bootJar

The jar path: build/libs/focus_mcp_data.jar
```

## MCP Configuration

Add the server to your MCP settings file (usually located
at `~/AppData/Roaming/Code/User/globalStorage/saoudrizwan.claude-dev/settings/cline_mcp_settings.json`):

```json
{
  "mcpServers": {
    "focus_mcp_data": {
      "command": "java",
      "args": [
        "-jar",
        "path/to/focus_mcp_data/focus_mcp_data.jar"
      ],
      "autoApprove": [
        "tableList",
        "gptText2DataInit",
        "gptText2DataData"
      ]
    }
  }
}
```

## Available Tools

### 1. tableList

Get table list in datafocus.

**Parameters:**

- `name` (optional): table name to filter
- `bearer` (required): bearer token

**Example:**

```json
{
  "name": "test",
  "bearer": "ZTllYzAzZjM2YzA3NDA0ZGE3ZjguNDJhNDjNGU4NzkyYjY1OTY0YzUxYWU5NmU="
}
```

### 2. gptText2DataInit

Initialize dialogue.

**Parameters:**

- `names` (required): selected table names
- `bearer` (required): bearer token
- `language` (optional): language ['english','chinese']

**Example:**

```json
{
  "names": [
    "test1",
    "test2"
  ],
  "bearer": "ZTllYzAzZjM2YzA3NDA0ZGE3ZjguNDJhNDjNGU4NzkyYjY1OTY0YzUxYWU5NmU="
}
```

### 3. gptText2DataData

Query data results.

**Parameters:**

- `chatId` (required): chat id
- `input` (required): Natural language
- `bearer` (required): bearer token

**Example:**

```json
{
  "chatId": "03975af5de4b4562938a985403f206d4",
  "input": "max(age)",
  "bearer": "ZTllYzAzZjM2YzA3NDA0ZGE3ZjguNDJhNDjNGU4NzkyYjY1OTY0YzUxYWU5NmU="
}
```

## Response Format

All tools return responses in the following format:

```json
{
  "errCode": 0,
  "exception": "",
  "msgParams": null,
  "promptMsg": null,
  "success": true,
  "data": {
  }
}
```

## Visual Studio Code Cline Sample

1. vsCode install cline plugin
2. mcp server config
   ![config mcp server](./mcp_server_config.png)
3. use
   1. get table list
      ![get table list1](./focus_mcp_data_table_1.png)
      ![get table list2](./focus_mcp_data_table_2.png)
   2. Initialize dialogue
      ![Initialize dialogue](./focus_mcp_data_init.png)
   3. query: what is the sum salary
     ![query](./focus_mcp_data_data.png)

## Contact：
[https://discord.gg/TUahEW2C](https://discord.gg/TUahEW2C)
![Datafocus](./wechat-qrcode.png)
