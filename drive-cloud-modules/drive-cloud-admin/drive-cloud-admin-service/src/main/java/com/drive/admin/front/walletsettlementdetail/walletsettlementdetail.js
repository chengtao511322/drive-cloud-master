import request from '@/utils/request'

            
// 分页查询列表
export function listPageWalletSettlementDetail(query) {
  return request({
    url: '/admin/walletSettlementDetail/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询列表
export function listWalletSettlementDetail(query) {
  return request({
    url: '/admin/walletSettlementDetail/findList',
    method: 'post',
    data: query
  })
}

// 查询详细
export function getWalletSettlementDetail(id) {
  return request({
    url: '/admin/walletSettlementDetail/' + id,
    method: 'get'
  })
}

// 新增
export function addWalletSettlementDetail(data) {
  return request({
    url: '/admin/walletSettlementDetail',
    method: 'post',
    data: data
  })
}

// 修改
export function updateWalletSettlementDetail(data) {
  return request({
    url: '/admin/walletSettlementDetail',
    method: 'put',
    data: data
  })
}

// 删除
export function delWalletSettlementDetail(id) {
  return request({
    url: '/admin/walletSettlementDetail/' + id,
    method: 'delete'
  })
}


// 状态启用/停用
export function changeStatus(data) {
  return request({
    url: '/admin/walletSettlementDetail/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出
export function exportWalletSettlementDetail(query) {
  return request({
    url: '/admin/walletSettlementDetail/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}