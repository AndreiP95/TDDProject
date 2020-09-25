package com.example.tdddemoproject.utils.trieImplementation


class TrieNode {
    var c = 0.toChar()
    var parent: TrieNode? = null
    var children = HashMap<Char, TrieNode>()
    var isLeaf = false

    constructor() {}
    constructor(c: Char) {
        this.c = c
    }
}




