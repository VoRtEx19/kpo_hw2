package org.example.service.revenue

import org.example.database.repository.revenue.RevenueRepository

interface RevenueService {
    var revenueRepository: RevenueRepository
    fun addRevenue(revenue: UInt)
    fun getTotalRevenue(): UInt
}