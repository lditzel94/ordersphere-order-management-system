# Generating Diagrams using Mingrammer's Diagrams

This guide explains how to set up and use [Diagrams](https://diagrams.mingrammer.com/) to generate architecture diagrams
using Python.

## Prerequisites

- Python installed (Python 3.6 or later is required).
- `pip` installed (Python's package manager).
- Basic knowledge of Python scripting.

---

## Installation Steps

1. **Install Python:**  
   If Python is not already installed, download and install it from [Python.org](https://www.python.org/downloads/), and
   ensure the "Add Python to PATH" option is selected during installation.

2. **Install Graphviz:**  
   Mingrammer’s Diagrams requires [Graphviz](https://graphviz.org/) for rendering diagrams.
    - Download and install Graphviz from the [official download page](https://graphviz.gitlab.io/download/).
    - Ensure the Graphviz binary folder (e.g., `C:\Program Files\Graphviz\bin`) is added to your `PATH` environment
      variable.

3. **Install the Diagrams Library:**  
   Run the following command in your terminal to install the `diagrams` package via `pip`:
   ```bash
   pip install diagrams
   ```

## Running the Python Script

1. Save your Python script (e.g., `diagram.py`).

2. Open the terminal in the directory where the script is located.

3. Run the script with Python:
   ```bash
   python diagram.py
   ```

4. Upon execution:
    - A `.png` file of the generated diagram will be created in the same directory as the script.
    - If `show=True` is specified, the diagram will open automatically in the default image viewer.

## Additional Resources

- Mingrammer's Diagrams Documentation: [https://diagrams.mingrammer.com/](https://diagrams.mingrammer.com/)
- Graphviz Official Site: [https://graphviz.org/](https://graphviz.org/)