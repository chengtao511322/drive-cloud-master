import request from '@/utils/request'

                                  
// 分页查询列表
export function listPageWalletSettlementSummary(query) {
  return request({
    url: '/admin/walletSettlementSummary/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询列表
export function listWalletSettlementSummary(query) {
  return request({
    url: '/admin/walletSettlementSummary/findList',
    method: 'post',
    data: query
  })
}

// 查询详细
export function getWalletSettlementSummary(id) {
  return request({
    url: '/admin/walletSettlementSummary/' + id,
    method: 'get'
  })
}

// 新增
export function addWalletSettlementSummary(data) {
  return request({
    url: '/admin/walletSettlementSummary',
    method: 'post',
    data: data
  })
}

// 修改
export function updateWalletSettlementSummary(data) {
  return request({
    url: '/admin/walletSettlementSummary',
    method: 'put',
    data: data
  })
}

// 删除
export function delWalletSettlementSummary(id) {
  return request({
    url: '/admin/walletSettlementSummary/' + id,
    method: 'delete'
  })
}


// 状态启用/停用
export function changeStatus(data) {
  return request({
    url: '/admin/walletSettlementSummary/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出
export function exportWalletSettlementSummary(query) {
  return request({
    url: '/admin/walletSettlementSummary/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}