# TDDProject
  TDDProject is an app (written in Kotlin) which was a training to accommodate with the TDD (test driven development) and MVVM (model-view-view model) architecture. Basically, it is an app where you can search for a city showing it in a city list sorted by the name typed.  
  
# Documentation
[General](#general)  
[Acceptance criteria](#acceptance-criteria)  
[Application behaviour](#application-behaviour)  
[Features the app was divided in](#features-the-app-was-divided-in)  
[UI Details](#ui-details)  
[Search algorithm](#search-algorithm)  

# General
  -	The application is build base on TDD and MVVM architecture pattern and is using [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)  
  - Using ViewModel and Databinding for UI implementations
  -	The Application is also using the following libraries: [Koin](https://insert-koin.io/), [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html), [Mockito](https://site.mockito.org/)
  -	Code cleanup for the app has been made using [Sonar Lint](https://www.sonarlint.org/)
  -	Written in Kotlin version : 1.4.10
  -	IDE used is Android Studio version: 4.10
  -	The app minimum requirement is SDK 24

# Acceptance criteria
  -	The cities are listed in ascending alphabetical order
  -	The cities list is scrollable
  -	The user of the application can search for a specific citiy(ies)
  -	If the list cannot be retrieved it should display an error page
  -	If the search does not display anything, display a specific page
  -	If the error page is displayed the user must see a retry button to reload the list

# Application behaviour
  The app starts with the splash screen where the data with cities is loading. This might take a while because there are a lot of cities (around 200k). After that, the user is shown the list of the cities in an ascending order by their name of the city & country. The user can search for a specific city or just for a short prefix and the searched cities will appear in the list as the user types.


# Features the app was divided in
1.	Splash screen & loading the data
2.	The trie algorithm for searching the city
3.	Displaying the cities in the screen

# UI Details
The app is created from one Activity with two Fragments
  - A splash screen that is show while the data is loading. It contains the app logo and a loading spinner to show the user that the data is still loading.  
    <p align="center">
        <img width="209" alt="Picture 1" src="https://user-images.githubusercontent.com/55602310/94921356-dc3ab900-04c0-11eb-8f20-0b2a0c475e5e.png">
    </p>
    
  - A search screen where the user can search and select the city that he/she wants to see. It contains an EditText and a RecyclerView.  
    <p align="center">
      <img width="203" alt="Picture 2" src="https://user-images.githubusercontent.com/55602310/94921358-dd6be600-04c0-11eb-9955-83e0c1a14237.png">
    </p>  
    
  - The user enters his/hers input on the edit text and the cities that start with the given input are listed in the recycler view. The list is sorted alphabetically by the name of the city and is updated at every character typed or deleted.  
     <p align="center">
      <img width="212" alt="Picture 3" src="https://user-images.githubusercontent.com/55602310/94921359-dd6be600-04c0-11eb-8cdd-0194bef4475a.png">
    </p>  
    
  - If there are no cities with the give prefix, an error page will be displayed.  
    <p align="center">
      <img width="212" alt="Picture 4" src="https://user-images.githubusercontent.com/55602310/94921361-de047c80-04c0-11eb-8370-7bc4d8a374ed.png">
    </p>  

 - Navigation between fragments is made using Navigation components for a better understanding of fragment transitions and to ensure a better scalability for future screens / features added.


# Search Algorithm

  The search algorithm is based on a [Trie](https://en.wikipedia.org/wiki/Trie) data structure, which is a *tree-like* structure, whose nodes store letters of the alphabet. By structuring the nodes in a particular pattern, words can be retrieved from the Trie by traversing down a branch.  
The Trie is a go-to structure when *autocompletion* is needed, thanks to its low space and time complexity.

## Trie Node
  - Each node contains a *City* object, the *string value* by which the search is made, a *list* of children nodes and a *boolean flag* to check if it is terminal or not.
  
## Adding a city in Trie
  - Adding a new City inside the Trie is done by parsing each letter of the name and verifying if there is an existing pattern in Trie. If there is a pattern, the parsing continues until there is a difference, when the remaining characters are added. On the terminal node, the City object is stored, along the name, children and the boolean flag that indicates that is a terminal node.

                       root
                        |
                        m
                        |  \
                        i   o
                        |   |  \
                        l   s   n   
                        |   |   |
                        a   c   t
                        |   |   |
                        n   o   r
                            |   |
                            w   e
                                |
                                a
                                |
                                l (City: Montreal, value: montreal, children: null, isTerminal: true)

## Searching for a City in Trie
   - The search algorithm is based on a [Depth first search](https://en.wikipedia.org/wiki/Depth-first_search) after the characters inside the user input.  
   - Searching for a city only compares the characters and move down. The search can terminate due to the end of a string or lack of key in the trie. In the former case, if the terminal field of the last node is true, then the key exists in the trie. In the second case, the search terminates without examining all the characters of the key, since the key is not present in the trie. In each cases, the algorithm returns a list, which can be empty or can contain cities.


## Temporal and Spatial Complexity

It is worth mentioning that, a search made by using entire words is more efficient by using a HashTable, but that data structure is not feasible for prefix searches.

  ### Temporal complexity
  - According to this [article](https://en.wikipedia.org/wiki/Trie#Algorithms), both insert and find run in O(m) time, where *m* is the length of the key.
  ### Spatial complexity
  - Thanks to the fact that the entries are kept only once in the Trie, the spatial complexity is close to O(n). Adding more cities will not change the spatial complexity exponentially, as the existance of several prefixes will reduce the amount of new nodes that will be created.
