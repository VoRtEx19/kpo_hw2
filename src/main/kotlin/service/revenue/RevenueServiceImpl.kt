package org.example.service.revenue

import org.example.database.repository.revenue.RevenueDTO
import org.example.database.repository.revenue.RevenueRepository

class RevenueServiceImpl(override var revenueRepository: RevenueRepository) : RevenueService {
    override fun addRevenue(revenue: UInt) {
        revenueRepository.create(RevenueDTO(revenue))
    }

    override fun getTotalRevenue(): UInt = revenueRepository.getAll().sumOf { it.revenue }

}