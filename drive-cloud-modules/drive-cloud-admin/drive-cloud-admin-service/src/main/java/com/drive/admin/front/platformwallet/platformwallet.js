import request from '@/utils/request'


// 分页查询教练钱包表列表
export function listPagePlatformWallet(query) {
  return request({
    url: '/admin/platformWallet/pageList',
    method: 'get',
    params: query
  })
}


// 分页查询教练钱包表列表
export function listPlatformWallet(query) {
  return request({
    url: '/admin/platformWallet/findList',
    method: 'get',
    params: query
  })
}

// 查询教练钱包表详细
export function getPlatformWallet(id) {
  return request({
    url: '/admin/platformWallet/' + id,
    method: 'get'
  })
}

// 新增教练钱包表
export function addPlatformWallet(data) {
  return request({
    url: '/admin/platformWallet',
    method: 'post',
    data: data
  })
}

// 修改教练钱包表
export function updatePlatformWallet(data) {
  return request({
    url: '/admin/platformWallet',
    method: 'put',
    data: data
  })
}

// 删除教练钱包表
export function delPlatformWallet(id) {
  return request({
    url: '/admin/platformWallet/' + id,
    method: 'delete'
  })
}


// 状态启用/停用教练钱包表
export function changeStatus(data) {
  return request({
    url: '/admin/platformWallet/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出教练钱包表
export function exportPlatformWallet(query) {
  return request({
    url: '/admin/platformWallet/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}