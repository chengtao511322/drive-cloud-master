import request from '@/utils/request'

                            
// 分页查询教练钱包表明细列表
export function listPagePlatformWalletDetail(query) {
  return request({
    url: '/admin/platformWalletDetail/pageList',
    method: 'get',
    params: query
  })
}


// 分页查询教练钱包表明细列表
export function listPlatformWalletDetail(query) {
  return request({
    url: '/admin/platformWalletDetail/findList',
    method: 'get',
    params: query
  })
}

// 查询教练钱包表明细详细
export function getPlatformWalletDetail(id) {
  return request({
    url: '/admin/platformWalletDetail/' + id,
    method: 'get'
  })
}

// 新增教练钱包表明细
export function addPlatformWalletDetail(data) {
  return request({
    url: '/admin/platformWalletDetail',
    method: 'post',
    data: data
  })
}

// 修改教练钱包表明细
export function updatePlatformWalletDetail(data) {
  return request({
    url: '/admin/platformWalletDetail',
    method: 'put',
    data: data
  })
}

// 删除教练钱包表明细
export function delPlatformWalletDetail(id) {
  return request({
    url: '/admin/platformWalletDetail/' + id,
    method: 'delete'
  })
}


// 状态启用/停用教练钱包表明细
export function changeStatus(data) {
  return request({
    url: '/admin/platformWalletDetail/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出教练钱包表明细
export function exportPlatformWalletDetail(query) {
  return request({
    url: '/admin/platformWalletDetail/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}