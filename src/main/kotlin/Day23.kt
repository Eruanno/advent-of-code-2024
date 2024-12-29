package org.eruanno

import java.io.File

class Day23 {

    fun partOne(fileName: String): Int {
        val connections = getLines(fileName).stream().map { it.split("-") }.map { Pair(it[0], it[1]) }.toList().toSet()
        val triples = mutableSetOf<Set<String>>()
        for (first in connections) {
            for (second in connections) {
                if (first == second) {
                    continue
                }
                if (first.first == second.first) {
                    if (connections.contains(Pair(first.second, second.second)) || connections.contains(Pair(second.second, first.second))) {
                        if (first.first[0] == 't' || first.second[0] == 't' || second.second[0] == 't') {
                            triples.add(mutableSetOf(first.first, first.second, second.second))
                        }
                    }
                } else if (first.first == second.second) {
                    if (connections.contains(Pair(first.second, second.first)) || connections.contains(Pair(second.first, first.second))) {
                        if (first.first[0] == 't' || first.second[0] == 't' || second.first[0] == 't') {
                            triples.add(mutableSetOf(first.first, first.second, second.first))
                        }
                    }
                }
            }
        }
        return triples.size
    }

    fun partTwo(fileName: String): String {
        val connections = getLines(fileName).stream().map { it.split("-") }.map { Pair(it[0], it[1]) }.toList()
        val network = buildNetwork(connections)
        val maxClique = findMaxClique(network)
        return maxClique.toList().sorted().joinToString(",")
    }

    private fun buildNetwork(connections: List<Pair<String, String>>): Map<String, Set<String>> {
        val network = mutableMapOf<String, MutableSet<String>>()
        for ((u, v) in connections) {
            network.computeIfAbsent(u) { mutableSetOf() }.add(v)
            network.computeIfAbsent(v) { mutableSetOf() }.add(u)
        }
        return network
    }

    private fun findMaxClique(network: Map<String, Set<String>>): Set<String> {
        val cliques = mutableListOf<Set<String>>()
        val vertices = network.keys.toMutableSet()
        bronKerbosch(emptySet(), vertices, mutableSetOf(), network, cliques)
        return cliques.maxByOrNull { it.size } ?: emptySet()
    }

    private fun bronKerbosch(
        r: Set<String>,
        p: MutableSet<String>,
        x: MutableSet<String>,
        graph: Map<String, Set<String>>,
        cliques: MutableList<Set<String>>
    ) {
        if (p.isEmpty() && x.isEmpty()) {
            cliques.add(r)
            return
        }

        val pivot = (p + x).firstOrNull() ?: return
        val neighbors = graph[pivot] ?: emptySet()

        for (v in p.toSet() - neighbors) {
            val newR = r + v
            val newP = p.intersect((graph[v] ?: emptySet()).toSet()).toMutableSet()
            val newX = x.intersect((graph[v] ?: emptySet()).toSet()).toMutableSet()

            bronKerbosch(newR, newP, newX, graph, cliques)

            p.remove(v)
            x.add(v)
        }
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
